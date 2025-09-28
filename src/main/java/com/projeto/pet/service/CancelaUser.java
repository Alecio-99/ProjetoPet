package com.projeto.pet.service;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.entity.EntityCadastroPet;
import com.projeto.pet.enuns.Status;

public interface CancelaUser {

    void validaStatus (RegisterDTO registerDTO, EntityCadastroPet entityCadastroPet);
}
