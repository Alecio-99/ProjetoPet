package com.projeto.pet.service;

import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserDetaillmpl implements UserDetailsService {

    @Autowired
    private RepositoryCadastroPet repositoryCadastroPet;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repositoryCadastroPet.findByEmail(email)
                .map(UserDetaillmpl::build)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
