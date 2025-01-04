package com.projeto.pet.controller;


import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cadastroPet")
public class ControllerCadastroPet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;


    @PostMapping("/")
    ResponseEntity<EntityCadastroPet> cadastroPet (@RequestBody EntityCadastroPet entityCadastroPet){
        EntityCadastroPet cadastroPet = repositoryCadastroPet.save(entityCadastroPet);
        return new ResponseEntity<EntityCadastroPet>(cadastroPet, HttpStatus.OK);
    }
}
