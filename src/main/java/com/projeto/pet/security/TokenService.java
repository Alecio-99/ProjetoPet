package com.projeto.pet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${projeto.jwtExpirationMs}")
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(com.projeto.pet.entity.EntityCadastroPet entityCadastroPet) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", entityCadastroPet.getEmail());
        claims.put("plan", entityCadastroPet.getPlanoContratados());
        claims.put("roles", entityCadastroPet.getRole());
        // Adicione outras informações do usuário que você queira no token, como ID ou plano
        // claims.put("userId", entityCadastroPet.getId());
        // claims.put("plan", entityCadastroPet.getPlan());

        return Jwts.builder()
                .claims(claims)
                .subject(entityCadastroPet.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public String validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            // Log the exception for debugging, but return empty string or throw specific exception
            System.err.println("Erro ao validar token: " + e.getMessage());
            return "";
        }
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getPlanFromToken(String token){
        return (String) extractAllClaims(token).get("plan");
    }

    public List<String> getRolesFromToken(String token){
        return (List<String>) extractAllClaims(token).get("roles");
    }

    public String getEmailFromToken(String token){
        return (String) extractAllClaims(token).get("email");
    }

    private Instant genExpirationDate() {
        // Esta função não é mais necessária com io.jsonwebtoken, mas mantida para referência
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
