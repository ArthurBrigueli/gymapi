package com.example.gympumpapi.configSecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gympumpapi.entity.User;

import com.example.gympumpapi.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        User user = this.userRepository.findByEmail(name).orElseThrow(()-> new UsernameNotFoundException("User NOT FOUND"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);

    }
}
