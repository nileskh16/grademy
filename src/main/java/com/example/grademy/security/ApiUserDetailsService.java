package com.example.grademy.security;

import com.example.grademy.entities.User;
import com.example.grademy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User loginUser = userRepository.findByUserName(s);
        if (loginUser != null) {
            return new ApiUserDetails(loginUser);
        } else {
            return null;
        }
    }
}
