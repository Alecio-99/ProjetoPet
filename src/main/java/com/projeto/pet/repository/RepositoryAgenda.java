package com.projeto.pet.repository;

import com.projeto.pet.entity.EntityAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAgenda extends JpaRepository<EntityAgendamento, Long> {

}
