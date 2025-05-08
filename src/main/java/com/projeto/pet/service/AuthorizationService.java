package com.projeto.pet.service;

import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EntityCadastroPet> userOptional = repositoryCadastroPet.findByEmail(username);

        if(!userOptional.isPresent()){
            System.out.println("Usuário não encontrado: " + username);
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        EntityCadastroPet user = userOptional.get();
        System.out.println("Usuário encontrado: " + user.getUsername());
        return user;
    }
}
