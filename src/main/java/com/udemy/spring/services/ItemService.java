package com.udemy.spring.services;

import com.udemy.spring.repositories.ItemRepository;
import com.udemy.spring.repositories.ListRepository;
import com.udemy.spring.entities.Item;
import com.udemy.spring.exception.NaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository rep;

    public Item updateCheckStatusById(Long id) throws NaoEncontrado{
        Item item = rep.findById(id).orElseThrow(NaoEncontrado::new);
        item.setChecked(!item.getChecked());
        return rep.save(item);
    }

    public void deleteById(Long id){
        rep.deleteById(id);
    }
}
