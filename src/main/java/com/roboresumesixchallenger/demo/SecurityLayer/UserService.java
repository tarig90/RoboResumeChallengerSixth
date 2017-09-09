package com.roboresumesixchallenger.demo.SecurityLayer;

import com.roboresumesixchallenger.demo.ModelLayer.RoboUser;
import com.roboresumesixchallenger.demo.RepositoryLayer.RoleRepository;
import com.roboresumesixchallenger.demo.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public RoboUser finByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public void saveUser(RoboUser user)
    {
        user.setRoles(Arrays.asList(roleRepository.findByRole("JobSeeker")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveRecruit(RoboUser user)
    {
        user.setRoles(Arrays.asList(roleRepository.findByRole("Recruiter")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
