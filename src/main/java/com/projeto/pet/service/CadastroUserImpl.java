package com.projeto.pet.service;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.entity.Entedeco;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.enuns.Status;
import com.projeto.pet.enuns.TipoPlano;
import com.projeto.pet.enuns.UserRoles;
import com.projeto.pet.exceptions.BusinessValidationException;
import com.projeto.pet.exceptions.PetException;
import com.projeto.pet.exceptions.ResourceNotFoundException;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class CadastroUserImpl implements CadastroUser{

    @Autowired
    RepositoryCadastroPet repositoryCadastroPet;

    @Override
    public EntityCadastroPet cadastrarUsuario(RegisterDTO registerDTO) {
        Optional<EntityCadastroPet> existingUser = repositoryCadastroPet.findByEmail(registerDTO.email());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email já cadastrado: " + registerDTO.email());
        }
        Optional<EntityCadastroPet> existCnpj = repositoryCadastroPet.findByCnpj(registerDTO.cnpj());
        if(existCnpj.isPresent()){
            throw new BusinessValidationException("Cnpj já existente " + registerDTO.cnpj());
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        EntityCadastroPet newUser = new EntityCadastroPet(
        registerDTO.email(), encryptedPassword, UserRoles.USER, Status.ATIVO);

        newUser.setName(registerDTO.name()); 
        newUser.setCnpj(registerDTO.cnpj());
        newUser.setEntedeco(new Entedeco(registerDTO.dadosEnderecoDTO()));

        return repositoryCadastroPet.save(newUser);

    }
}
