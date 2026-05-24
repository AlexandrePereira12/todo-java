package com.udemy.spring.services;

import com.udemy.spring.DTO.ItemDTO;
import com.udemy.spring.DTO.ListDTO;
import com.udemy.spring.entities.Item;
import com.udemy.spring.entities.List;
import com.udemy.spring.entities.User;
import com.udemy.spring.exception.NaoEncontrado;
import com.udemy.spring.repositories.ListRepository;
import com.udemy.spring.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {
    ListRepository rep;
    UserRepository repUser;
    @Autowired
    public ListService(ListRepository rep, UserRepository userRep){
        this.rep = rep;
        this.repUser = userRep;
    }

    public List saveByDTO(ListDTO dto, String username){
        List list = dto.toList();
        User user = repUser.findByEmail(username).get();
        list.setUser(user);
        return rep.save(list);
    }

    public java.util.List<List> findByUserEmail(String email){
        return rep.findByUserEmail(email);
    }

    public void deleteById(Long id){
        rep.deleteById(id);
    }

    public List findById(Long id) throws NaoEncontrado {
        return rep.findById(id).orElseThrow(NaoEncontrado::new);
    }

    public List createItemById(ItemDTO dto, Long id){
        List list = rep.findById(id).get();
        Item item = dto.toItem();
        item.setList(list);
        java.util.List<Item> itens = list.getItems();
        itens.add(item);
        list.setItems(itens);
        return rep.save(list);
    }
}
