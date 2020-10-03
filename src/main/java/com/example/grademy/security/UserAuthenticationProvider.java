package com.example.grademy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    @Autowired
    private ApiUserDetailsService apiUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken userPrincipal = null;
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = apiUserDetailsService.loadUserByUsername(username);
        if (userDetails != null)  {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                userPrincipal = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            }
        }
        if (userPrincipal != null) {
            logger.info(String.format("User %s is successfully authenticated", userDetails.getUsername()));
            return userPrincipal;
        } else {
            throw new UsernameNotFoundException("Either username of password is incorrect");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
