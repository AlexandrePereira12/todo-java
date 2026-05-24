package com.udemy.spring.controller;

import com.udemy.spring.entities.Item;
import com.udemy.spring.exception.NaoEncontrado;
import com.udemy.spring.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @Autowired
    ItemService service;

    @PatchMapping("/item/{id}/concluir")
    public ResponseEntity<Item> updateCheckStatusOfItem(@PathVariable("id") Long id) throws NaoEncontrado {
        return new ResponseEntity<Item>(service.updateCheckStatusById(id), HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}