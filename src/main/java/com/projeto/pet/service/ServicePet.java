package com.projeto.pet.service;
import com.projeto.pet.DTO.TrocaSenhaDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.exceptions.PetException;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean alterarPassword(Long id, TrocaSenhaDTO trocaSenhaDTO) {
        Optional<EntityCadastroPet> trocaPassword = repositoryCadastroPet.findById(id);

        if (!trocaPassword.isPresent()) {
            System.out.println("ID " + id + " não encontrado no banco.");
            throw PetException.usuarioNaoEncontrado(id);
        }

        EntityCadastroPet cadastroPet = trocaPassword.get();
        System.out.println("Usuário encontrado: " + cadastroPet);

        if (!passwordEncoder.matches(trocaSenhaDTO.getPasswordAtual(), cadastroPet.getPassword())) {
            throw PetException.senhaAtualIncorreta();
        }


        String senhaCodificada = passwordEncoder.encode(trocaSenhaDTO.getPasswordNovo());
        cadastroPet.setPassword(senhaCodificada);

        //cadastroPet.setPassword(trocaSenhaDTO.getPasswordNovo());
        //cadastroPet.setPassword(trocaSenhaDTO.getPasswordNovo());
        repositoryCadastroPet.save(cadastroPet);
        return true;
    }
    public EntityCadastroPet cadastroPet (EntityCadastroPet entityCadastroPet){
        String senhaCripto = passwordEncoder.encode(entityCadastroPet.getPassword());
        entityCadastroPet.setPassword(senhaCripto);
        return repositoryCadastroPet.save(entityCadastroPet);
    }
}
