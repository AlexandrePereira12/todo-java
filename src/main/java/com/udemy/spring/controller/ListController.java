package com.udemy.spring.controller;

import com.udemy.spring.DTO.ItemDTO;
import com.udemy.spring.DTO.ListDTO;
import com.udemy.spring.entities.List;
import com.udemy.spring.exception.NaoEncontrado;
import com.udemy.spring.repositories.ListRepository;
import com.udemy.spring.services.ListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ListController {
    @Autowired
    ListService service;
    ListRepository rep;

    @PostMapping("/lista")
    public ResponseEntity<List> create(@RequestParam Map<String, String> body){
        ListDTO dto = new ListDTO(body.get("name"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List list = service.saveByDTO(dto, auth.getName());
        return new ResponseEntity<List>(list, HttpStatus.CREATED);
    }

    @DeleteMapping("/lista/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ModelAndView show(@PathVariable("id") Long id) throws NaoEncontrado {
        ModelAndView view = new ModelAndView("lista");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        view.addObject("auth", auth);
        view.addObject("list", service.findById(id));
        return view;
    }

    @PostMapping("/lista/{id}/item")
    public ResponseEntity<Boolean> createItem(@PathVariable("id") Long id, @RequestParam Map<String, String> body){
        ItemDTO dto = new ItemDTO(body.get("description"));
        service.createItemById(dto, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(NaoEncontrado.class)
    public ModelAndView handlerNaoEncontrado(){
        return new ModelAndView("404");
    }
}
