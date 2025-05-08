package com.projeto.pet.controller;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.DTO.TrocaSenhaDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import com.projeto.pet.service.ServicePet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("cadastroPet")
public class ControllerCadastroPet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;
    @Autowired
    ServicePet servicePet;

    @PostMapping("/register")
    ResponseEntity<EntityCadastroPet> cadastroPet (@RequestBody @Valid RegisterDTO registerDTO){
        Optional<EntityCadastroPet> existingUser = repositoryCadastroPet.findByEmail(registerDTO.email());
        if(existingUser.isPresent()) {
            System.out.println("Email já cadastrado: " + registerDTO.email());
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        EntityCadastroPet newUser = new EntityCadastroPet(registerDTO.email(), encryptedPassword, registerDTO.role());
        newUser.setName(registerDTO.email()); // Usando email como nome temporariamente

        EntityCadastroPet savedUser = this.repositoryCadastroPet.save(newUser);
        System.out.println("Usuário cadastrado com sucesso: " + savedUser.getEmail());
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> alteraPassWord (@PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO){
        boolean AtualizarPassWord = servicePet.alterarPassword(id, trocaSenhaDTO);
        return new ResponseEntity<>(AtualizarPassWord, HttpStatus.OK);
    }
}
