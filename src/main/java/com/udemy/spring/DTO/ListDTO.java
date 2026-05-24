package com.udemy.spring.DTO;

import com.udemy.spring.entities.List;

public record ListDTO(String name) {
    public List toList(){
        List list = new List();
        list.setName(name);
        return list;
    }
}
