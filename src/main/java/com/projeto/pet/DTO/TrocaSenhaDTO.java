package com.projeto.pet.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrocaSenhaDTO {

        @NotBlank
        @Size(min = 8, max = 8)
        private String passwordAtual;
        @NotBlank
        @Size(min = 8, max = 8)
        private String passwordNovo;
        @NotBlank
        @Size(min = 8, max = 8)
        private String confimraPassrowdNovo;


    public String getPasswordAtual() {
        return passwordAtual;
    }

    public void setPasswordAtual(String passwordAtual) {
        this.passwordAtual = passwordAtual;
    }

    public String getPasswordNovo() {
        return passwordNovo;
    }

    public void setPasswordNovo(String passwordNovo) {
        this.passwordNovo = passwordNovo;
    }

    public String getConfimraPassrowdNovo() {
        return confimraPassrowdNovo;
    }

    public void setConfimraPassrowdNovo(String confimraPassrowdNovo) {
        this.confimraPassrowdNovo = confimraPassrowdNovo;
    }
}
