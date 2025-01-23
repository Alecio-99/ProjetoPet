package com.projeto.pet.service;
import com.projeto.pet.DTO.TrocaSenhaDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.exceptions.PetException;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePet {

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;

    public boolean alterarPassword(Long id, TrocaSenhaDTO trocaSenhaDTO) {
        Optional<EntityCadastroPet> trocaPassword = repositoryCadastroPet.findById(id);

        if (!trocaPassword.isPresent()) {
            System.out.println("ID " + id + " não encontrado no banco.");
            throw PetException.usuarioNaoEncontrado(id);
        }

        EntityCadastroPet cadastroPet = trocaPassword.get();
        System.out.println("Usuário encontrado: " + cadastroPet);

        if (!cadastroPet.getPassword().equals(trocaSenhaDTO.getPasswordAtual())) {
            throw PetException.senhaAtualIncorreta();
        }
        if (!trocaSenhaDTO.getPasswordNovo().equals(trocaSenhaDTO.getConfirmarPasswordNovo())) {
            throw PetException.novaSenhaNaoConfere();
        }

        cadastroPet.setPassword(trocaSenhaDTO.getPasswordNovo());
        cadastroPet.setConfirmarPassword(trocaSenhaDTO.getConfirmarPasswordNovo());
        repositoryCadastroPet.save(cadastroPet);
        return true;
    }
}
