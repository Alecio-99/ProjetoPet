package com.projeto.pet.entity;

import com.projeto.pet.DTO.DadosEnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@AllArgsConstructor
@Data
public class Entedeco {
    private String rua;
    private String numero;
    private String bairro;
    private String uf;
    private String cidade;
    private String cep;

    public Entedeco(){

    }

    public Entedeco(DadosEnderecoDTO dadosEnderecoDTO){
        this.rua = dadosEnderecoDTO.rua();
        this.numero = dadosEnderecoDTO.rua();
        this.bairro = dadosEnderecoDTO.bairro();
        this.uf = dadosEnderecoDTO.uf();
        this.cidade = dadosEnderecoDTO.cidade();
        this.cep = dadosEnderecoDTO.cep();
    }
}
