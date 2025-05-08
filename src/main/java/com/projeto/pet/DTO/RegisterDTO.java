package com.projeto.pet.DTO;

import com.projeto.pet.enuns.UserRoles;

public record RegisterDTO(String email, String password, UserRoles role) {
}
