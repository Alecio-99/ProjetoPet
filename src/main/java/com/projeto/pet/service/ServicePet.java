package com.projeto.pet.service;
import com.projeto.pet.DTO.TrocaSenhaDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;

    public boolean alterarPassword(Long id, TrocaSenhaDTO trocaSenhaDTO){
        Optional<EntityCadastroPet> trocaPassword = repositoryCadastroPet.findById(id);

        if(trocaPassword.isPresent()){
          EntityCadastroPet cadastroPet = trocaPassword.get();

          if(cadastroPet.getPassword().equals(trocaSenhaDTO.getPasswordAtual())){
            if(trocaSenhaDTO.getPasswordNovo().equals(trocaSenhaDTO.getConfimraPassrowdNovo())){
                cadastroPet.setPassword(trocaSenhaDTO.getPasswordNovo());
                repositoryCadastroPet.save(cadastroPet);
                return true;
             }

           }
        }
        return false;
    }

}
