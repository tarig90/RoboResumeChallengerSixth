package com.roboresumesixchallenger.demo.SecurityLayer;

import com.roboresumesixchallenger.demo.ModelLayer.Role;
import com.roboresumesixchallenger.demo.ModelLayer.RoboUser;
import com.roboresumesixchallenger.demo.RepositoryLayer.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Transactional
@Service
public class SSUserDetailService implements UserDetailsService
{
    private UserRepository userRepository;


    public SSUserDetailService(UserRepository userRepository){this.userRepository=userRepository;}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.out.println("Entered loadUserByUsername");
        try
        {
            RoboUser user = userRepository.findByUsername(username);
            System.out.println("Username:"+user.getUsername());
            if(user ==null)
            {System.out.println("user not found with the provided username" + user.toString());
            return null;
            }
            System.out.println("User actually found");

               return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));

        }
        catch (Exception e){throw new UsernameNotFoundException("user not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(RoboUser user)
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

    for(Role recruit : user.getRoles())
    {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(recruit.getRole());
        authorities.add(grantedAuthority);
    }
    System.out.println("user authorities are" + authorities.toString());
    return authorities;

    }

}
