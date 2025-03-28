package com.projeto.pet.controller;


import com.projeto.pet.DTO.TrocaSenhaDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import com.projeto.pet.service.ServicePet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cadastroPet")
public class ControllerCadastroPet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;
    @Autowired
    ServicePet servicePet;


    @PostMapping("/")
    ResponseEntity<EntityCadastroPet> cadastroPet (@RequestBody EntityCadastroPet entityCadastroPet){
        EntityCadastroPet cadastroPet = servicePet.cadastroPet(entityCadastroPet);
        return new ResponseEntity<EntityCadastroPet>(cadastroPet, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    ResponseEntity<Boolean> alteraPassWord (@PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO){
    boolean AtualizarPassWord = servicePet.alterarPassword(id, trocaSenhaDTO);
    return new ResponseEntity<>(AtualizarPassWord, HttpStatus.OK);

    }

}
