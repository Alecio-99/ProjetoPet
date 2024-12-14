package com.projeto.pet.repository;

import com.projeto.pet.entity.EntityLoginPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLoginPet extends JpaRepository<EntityLoginPet, Long> {
}
