package com.projeto.pet.service;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.enuns.Status;
import org.springframework.stereotype.Component;

@Component
public class CancelaUserImpl implements CancelaUser{

    @Override
    public void validaStatus(RegisterDTO registerDTO, EntityCadastroPet entityCadastroPet) {
        if(registerDTO.status() == null){
            throw new IllegalArgumentException("O campo status não pode ser vazio! ");
        }
        if(entityCadastroPet.getStatus() == Status.INATIVO){
            throw new IllegalArgumentException("O status já esta como INATIVO");
        }
    }
}
