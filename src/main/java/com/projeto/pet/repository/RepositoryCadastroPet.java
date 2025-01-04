package com.projeto.pet.repository;


import com.projeto.pet.entity.EntityCadastroPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCadastroPet extends JpaRepository<EntityCadastroPet, Long> {

}
