package com.stackroute.service;

import com.stackroute.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthenticationService service;

@Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
    System.out.println("inside loadUserByUsername ");
        final User user = service.getUserByUserEmail(userEmail);
    System.out.println("User -> "+ user);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + userEmail + "' not found");
        }
        return org.springframework.security.core.userdetails.User//
                .withUsername(userEmail)//
                .password(user.getPassword())//
                .authorities("PLAYER","VENDOR")
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }


    }

