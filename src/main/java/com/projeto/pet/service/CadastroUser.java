package com.projeto.pet.service;

import com.projeto.pet.DTO.RegisterDTO;
import com.projeto.pet.entity.EntityCadastroPet;

public interface CadastroUser {

    EntityCadastroPet cadastrarUsuario (RegisterDTO registerDTO);

}
