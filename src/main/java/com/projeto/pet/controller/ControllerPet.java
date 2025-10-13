package com.projeto.pet.controller;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.DTO.RegisterResponseDTO;
import com.projeto.pet.DTO.TrocaSenhaDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import com.projeto.pet.service.ServicePet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("cadastroPet")
public class ControllerPet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;
    @Autowired
    ServicePet servicePet;

    @PostMapping("/register")
    ResponseEntity<RegisterResponseDTO> cadastroPet (@RequestBody @Valid RegisterDTO registerDTO){
        try{
            EntityCadastroPet savedUser = servicePet.cadastroPet(registerDTO);

            RegisterResponseDTO response = new RegisterResponseDTO(
                    savedUser.getEmail(),
                    savedUser.getRole().name(),
                    savedUser.getPlanoContratados()
            );

            return ResponseEntity.ok(response);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> alteraPassWord (@PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO){
        boolean AtualizarPassWord = servicePet.alterarPassword(id, trocaSenhaDTO);
        return new ResponseEntity<>(AtualizarPassWord, HttpStatus.OK);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity atualizaStatus (@PathVariable Long id, @Valid @RequestBody RegisterDTO registerDTO){
        var usuario =repositoryCadastroPet.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado" + id));

        servicePet.validaStatus(registerDTO, usuario);

        usuario.setStatus(registerDTO.status());

        usuario.setPlanoFim(LocalDate.now());


        repositoryCadastroPet.save(usuario);

       return ResponseEntity.ok("Status do Usuario: " + id + "Atualizado para inativo");
    }
}
