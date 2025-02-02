package com.projeto.pet.entity;

import com.projeto.pet.enuns.EnumBaia;
import com.projeto.pet.enuns.EnumPorteDog;
import com.projeto.pet.enuns.EnumStatusDog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "agendamento")
public class EntityAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameDog;
    private String raca;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    @Enumerated(EnumType.STRING)
    private EnumPorteDog porteDog;
    @Enumerated(EnumType.STRING)
    private EnumBaia baia;
    @Enumerated(EnumType.STRING)
    private EnumStatusDog statusDog;
    @ManyToOne
    @JoinColumn(name = "id_dono", nullable = false)
    private EntityCadastroPet dono;
    private String nomeDono;

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
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

    public EntityCadastroPet getDono() {
        return dono;
    }

    public void setDono(EntityCadastroPet dono) {
        this.dono = dono;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityAgendamento that = (EntityAgendamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
