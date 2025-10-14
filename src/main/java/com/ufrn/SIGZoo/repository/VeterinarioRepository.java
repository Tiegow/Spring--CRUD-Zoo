package com.ufrn.SIGZoo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    
    boolean existsByCrmv(String crmv);
    Optional<Veterinario> findByCrmv(String crmv);
}
