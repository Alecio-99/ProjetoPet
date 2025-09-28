package com.projeto.pet.DTO;

import com.projeto.pet.entity.Entedeco;
import com.projeto.pet.enuns.Status;
import com.projeto.pet.enuns.TipoPlano;
import com.projeto.pet.enuns.UserRoles;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RegisterDTO(
        @NotBlank(message = "O nome não pode ser nulo ou vazio")
        String name,
        @NotBlank(message = "O email não pode ser nulo ou vazio")
        @Email
        String email,
        @NotBlank(message = "O cnpj não pode ser nulo ou vazio")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")
        String cnpj,
        @NotBlank(message = "O tipo plano não pode ser nulo ou vazio")
        TipoPlano tipoPlano,
        String statusPlano,
        @NotBlank(message = "O campo senha não pode ser nulo ou vazio")
        String password,
        UserRoles role,
        Status status,
        @NotNull
        @Valid
        DadosEnderecoDTO dadosEnderecoDTO
) {}
