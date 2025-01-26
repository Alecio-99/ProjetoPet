package com.projeto.pet.entity;

import com.projeto.pet.enuns.EnumBaia;
import com.projeto.pet.enuns.EnumPorteDog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String agenda;
    @Enumerated(EnumType.STRING)
    private EnumPorteDog porteDog;
    @Enumerated(EnumType.STRING)
    private EnumBaia baia;
    @ManyToOne
    @JoinColumn(name = "id_dono")
    private EntityCadastroPet dono;
}
