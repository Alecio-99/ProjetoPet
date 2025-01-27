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
    @JoinColumn(name = "id_dono")
    private EntityCadastroPet dono;
}
