package com.udemy.spring.services;

import com.udemy.spring.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.udemy.spring.repositories.UserRepository;
import com.udemy.spring.entities.User;

@Service
public class UserService {
    @Autowired
    UserRepository rep;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository rep, PasswordEncoder passwordEncoder){
        this.rep = rep;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveByDTO(UserDTO dto){
        User user = dto.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return rep.save(user);
    }
}
