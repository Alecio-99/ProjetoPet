package com.projeto.pet.entity;

import com.projeto.pet.enuns.Status;
import com.projeto.pet.enuns.TipoPlano;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "assinaturas")
public class Assinaturas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPlano plano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private BigDecimal preco;

    private LocalDateTime dataContratacao;
    private LocalDateTime dataAtivacao;
    private LocalDateTime dataExpiracao;

    private String idTransacaoPagamento;
}
