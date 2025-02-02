package com.projeto.pet.repository;

import com.projeto.pet.entity.EntityAgendamento;
import com.projeto.pet.enuns.EnumStatusDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface RepositoryAgenda extends JpaRepository<EntityAgendamento, Long> {
    boolean existsByInicioBeforeAndFimAfter(LocalDateTime fim, LocalDateTime inicio);

    List<EntityAgendamento> findByStatusDogAndInicioBefore(EnumStatusDog statusDog, LocalDateTime agora);
}
