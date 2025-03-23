package com.projeto.pet.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.pet.entity.EntityAgendamento;
import com.projeto.pet.enuns.EnumBaia;
import com.projeto.pet.enuns.EnumPorteDog;
import com.projeto.pet.enuns.EnumStatusDog;

import java.time.LocalDateTime;

public class AgendamentoDTO {


        private long id;
        private String nameDog;
        private String raca;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime inicio;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime fim;
        private EnumPorteDog porteDog;
        private EnumBaia baia;
        private EnumStatusDog statusDog;
        private long donoId;
        private String nomeDono;

       public AgendamentoDTO() {}

        public AgendamentoDTO(EntityAgendamento agendamento) {
            this.id = agendamento.getId();
            this.nameDog = agendamento.getNameDog();
            this.raca = agendamento.getRaca();
            this.inicio = agendamento.getInicio();
            this.fim = agendamento.getFim();
            this.porteDog = agendamento.getPorteDog();
            this.baia = agendamento.getBaia();
            this.statusDog = agendamento.getStatusDog();
            this.donoId = agendamento.getDono() != null ? agendamento.getDono().getId() : 0;
            this.nomeDono = agendamento.getDono() != null ? agendamento.getDono().getName() : null;
        }

    public EntityAgendamento toEntity() {
        EntityAgendamento entity = new EntityAgendamento();
        entity.setNameDog(this.nameDog);
        entity.setRaca(this.raca);
        entity.setInicio(this.inicio);
        entity.setFim(this.fim);
        entity.setPorteDog(this.porteDog);
        entity.setBaia(this.baia);
        entity.setStatusDog(this.statusDog);
        return entity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameDog() {
        return nameDog;
    }

    public void setNameDog(String nameDog) {
        this.nameDog = nameDog;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public EnumPorteDog getPorteDog() {
        return porteDog;
    }

    public void setPorteDog(EnumPorteDog porteDog) {
        this.porteDog = porteDog;
    }

    public EnumBaia getBaia() {
        return baia;
    }

    public void setBaia(EnumBaia baia) {
        this.baia = baia;
    }

    public EnumStatusDog getStatusDog() {
        return statusDog;
    }

    public void setStatusDog(EnumStatusDog statusDog) {
        this.statusDog = statusDog;
    }

    public long getDonoId() {
        return donoId;
    }

    public void setDonoId(long donoId) {
        this.donoId = donoId;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }
}
