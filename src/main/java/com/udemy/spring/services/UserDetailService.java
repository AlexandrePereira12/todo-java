package com.udemy.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udemy.spring.entities.User;
import com.udemy.spring.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository rep;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = rep.findByEmail(username);
        if(userOptional.isEmpty()) throw new UsernameNotFoundException("Usuario nao encontrado");
        User user = userOptional.get();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).build();
        return userDetails;
    }
}
