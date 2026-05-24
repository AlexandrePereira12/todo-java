package com.udemy.spring.DTO;

import com.udemy.spring.entities.Item;

public record ItemDTO(String description) {
    public Item toItem(){
        Item item = new Item();
        item.setDescription(description);
        return item;
    }
}
