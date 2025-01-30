package com.projeto.pet.repository;

import com.projeto.pet.entity.EntityAgendamento;
import com.projeto.pet.enuns.EnumStatusDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryAgenda extends JpaRepository<EntityAgendamento, Long> {
    boolean existsByInicioLessThanAndFimGreaterThan(LocalDateTime fim, LocalDateTime inicio);

    List<EntityAgendamento> findByStatus(EnumStatusDog statusDog, LocalDateTime agora);
}
