package com.projeto.pet.controller;

import com.projeto.pet.DTO.AuthenticationDTO;
import com.projeto.pet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthenticationDTO authenticationDTO){
        return ResponseEntity.ok(authService.login(authenticationDTO));
    }
}
