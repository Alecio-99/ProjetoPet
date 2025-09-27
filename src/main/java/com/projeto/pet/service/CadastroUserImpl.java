package com.projeto.pet.service;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.entity.Entedeco;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.enuns.TipoPlano;
import com.projeto.pet.enuns.UserRoles;
import com.projeto.pet.repository.RepositoryCadastroPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        UserRoles role = UserRoles.USER;

        TipoPlano tipoPlano = registerDTO.tipoPlano();

        if(tipoPlano == TipoPlano.ESSENCIAL ||
                tipoPlano == TipoPlano.PROFISSIONAL ||
                tipoPlano == TipoPlano.PERSONALIZADO){
            role = UserRoles.ADMIN;
        }

        EntityCadastroPet newUser = new EntityCadastroPet(
        registerDTO.email(), encryptedPassword, role);
        newUser.setName(registerDTO.name());
        newUser.setTipoPlano(tipoPlano);
        newUser.setCnpj(registerDTO.cnpj());
        newUser.setEntedeco(new Entedeco(registerDTO.dadosEnderecoDTO()));

        return repositoryCadastroPet.save(newUser);

    }
}
