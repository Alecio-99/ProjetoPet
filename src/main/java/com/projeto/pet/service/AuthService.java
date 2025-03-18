package com.projeto.pet.service;

import com.projeto.pet.DTO.AcessDTO;
import com.projeto.pet.DTO.AuthenticationDTO;
import com.projeto.pet.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authenticationDTO){
        try {
            //Cria mecanismo de credencial
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),authenticationDTO.getPassword());

            //Prepara mecanismo para autenticaccao
            Authentication authentication = authenticationManager.authenticate(userAuth);

            //Busca usuario logado
            UserDetaillmpl userDetail =  (UserDetaillmpl) authentication.getPrincipal();
            String token = jwtUtils.generateTokenFromUserDatailsImpl(userDetail);

            AcessDTO acessDTO = new AcessDTO(token);

            return acessDTO;
        }catch (BadCredentialsException e){

        }
        return new AcessDTO("Acesso negaddo");
    }
}
