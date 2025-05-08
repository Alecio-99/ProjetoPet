package com.projeto.pet.controller;

import com.projeto.pet.DTO.AuthenticationDTO;
import com.projeto.pet.DTO.LoginResponseDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import com.projeto.pet.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RepositoryCadastroPet repositoryCadastroPet;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            System.out.println("Tentativa de login para o email: " + data.getEmail());
            
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            
            var token = tokenService.generateToken((EntityCadastroPet) auth.getPrincipal());
            System.out.println("Login realizado com sucesso para: " + data.getEmail());
            
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        } catch (Exception e) {
            System.out.println("Erro inesperado durante o login: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}
