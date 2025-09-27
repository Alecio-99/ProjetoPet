package com.projeto.pet.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDTO(
         @NotBlank(message = "O nome da rua não pode ser nulo ou vazio")
         String rua,
         @NotBlank(message = "O numero não pode ser nulo ou vazio")
         String numero,
         @NotBlank(message = "O cidade não pode ser nulo ou vazio")
         String cidade,
         @NotBlank(message = "O campo da bairro não pode ser nulo ou vazio")
         String bairro,
         @NotBlank(message = "O campo uf não pode ser nulo ou vazio")
         String uf,
         @NotBlank(message = "O cep não pode ser nulo ou vazio")
         @Pattern(regexp = ("\\d{8}"))
         String cep
){
}
