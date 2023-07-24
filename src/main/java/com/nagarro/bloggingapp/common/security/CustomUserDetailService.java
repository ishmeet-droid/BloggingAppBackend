package com.nagarro.bloggingapp.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.user.UserEntity;
import com.nagarro.bloggingapp.user.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
     
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         

        UserEntity user = userRepository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found with email: " + username)
        );

        return user;

    }
    
}
