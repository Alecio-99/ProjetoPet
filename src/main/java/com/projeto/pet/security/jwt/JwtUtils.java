package com.projeto.pet.security.jwt;

import com.projeto.pet.service.UserDetaillmpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationMs}")
    private long jwtExpirationMs;

    public String generateTokenFromUserDatailsImpl (UserDetaillmpl userDetaillmpl){
        return Jwts.builder().setSubject(userDetaillmpl.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSignWithKey(), SignatureAlgorithm.HS512).compact();

    }
    public Key getSignWithKey (){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }
    public String getUsernameToken(String token){
        return Jwts.parser().setSigningKey(getSignWithKey()).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(getSignWithKey()).build().parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException e){
            System.out.println("Token invalido"  + e.getMessage());
        }catch (ExpiredJwtException e){
            System.out.println("Token Expirado" + e.getMessage());
        }catch (UnsupportedJwtException e){
            System.out.println("Token não suportado" + e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Token Argumento invalido" + e.getMessage());
        }
        return false;
    }
}
