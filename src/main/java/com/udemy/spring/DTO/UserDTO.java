package com.udemy.spring.DTO;

import com.udemy.spring.entities.User;

public record UserDTO(String email, String password, String name) {
    public User toUser(){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        return user;
    }
}
