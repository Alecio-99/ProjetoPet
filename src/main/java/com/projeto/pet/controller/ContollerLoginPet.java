package com.projeto.pet.controller;

import com.projeto.pet.entity.EntityLoginPet;
import com.projeto.pet.repository.RepositoryLoginPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ContollerLoginPet {

    @Autowired
    private RepositoryLoginPet repositoryLoginPet;

    @PostMapping("/")
    ResponseEntity<EntityLoginPet> login (@RequestBody EntityLoginPet entityLoginPet) {
        EntityLoginPet loginPet = repositoryLoginPet.save(entityLoginPet);
        return new ResponseEntity<>(loginPet, HttpStatus.OK);
    }

}
