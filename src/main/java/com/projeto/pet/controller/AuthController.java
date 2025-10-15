package com.projeto.pet.controller;

import com.projeto.pet.DTO.AuthenticationDTO;
import com.projeto.pet.DTO.LoginResponseDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import com.projeto.pet.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RepositoryCadastroPet repositoryCadastroPet;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            System.out.println("Tentativa de login para o email: " + data.getEmail());
            
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var user = (EntityCadastroPet) auth.getPrincipal();


            List<String> planosAtivos = null;
            try {
                planosAtivos = webClientBuilder.build()
                        .get()
                        .uri("http://planos-service/api/assinaturas/cliente/{clienteId}/ativos", user.getId( ))
                        .retrieve()

                        .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                        .block();
            } catch (Exception e) {
                System.err.println("AVISO: Não foi possível buscar os planos do usuário " + user.getId() + ". Erro: " + e.getMessage());
            }

            Map<String, Object> claims = new HashMap<>();

            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            claims.put("roles", roles);

            if (planosAtivos != null && !planosAtivos.isEmpty()) {
                List<String> planAuthorities = planosAtivos.stream()
                        .map(plano -> "PLANO_" + plano.toUpperCase())
                        .collect(Collectors.toList());
                claims.put("plan_authorities", planAuthorities);
            }

            // 4. GERAR O TOKEN: Chamar o TokenService com os dados já prontos.
            var token = tokenService.generateToken(user.getEmail(), claims);

            System.out.println("Login realizado com sucesso para: " + user.getEmail());
            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (AuthenticationException e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciais inválidas"); // 401 Unauthorized é mais apropriado
        } catch (Exception e) {
            System.out.println("Erro inesperado durante o login: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}
