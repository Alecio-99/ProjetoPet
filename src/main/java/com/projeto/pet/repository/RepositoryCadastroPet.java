package com.projeto.pet.repository;


import com.projeto.pet.entity.EntityCadastroPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCadastroPet extends JpaRepository<EntityCadastroPet, Long> {
    Optional<EntityCadastroPet> findByName(String name);
}
