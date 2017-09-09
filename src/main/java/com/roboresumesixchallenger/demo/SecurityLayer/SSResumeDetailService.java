//package com.roboresumesixchallenger.demo.SecurityLayer;
//
//
//import com.roboresumesixchallenger.demo.ModelLayer.RoboUser;
//import com.roboresumesixchallenger.demo.ModelLayer.Role;
//import com.roboresumesixchallenger.demo.RepositoryLayer.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Transactional
//@Service
//public class SSResumeDetailService implements UserDetailsService
//{
//
//    private UserRepository userRepository;
//
//    public SSResumeDetailService(UserRepository userRepository)
//    {
//        this.userRepository=userRepository;
//    }
//
//    @Override
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//    {
//        try
//        {
//            RoboUser user = userRepository.findByUsername(username);
//            if(user ==null)
//            {System.out.println("user not found");
//            return null;
//            }
//
//            System.out.println("user from username");
//            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
//        }
//        catch (Exception e)
//        {
//            throw new UsernameNotFoundException("user not found");
//        }
//
//
//    }
//
//    private Set<GrantedAuthority> getAuthorities(RoboUser user)
//    {
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        for(Role role: user.getRoles())
//        {GrantedAuthority grantedAuthority =  new SimpleGrantedAuthority(role.getRole());
//        authorities.add(grantedAuthority);}
//        System.out.println("USER AUTHORITIES");
//        return authorities;
//    }
//}
