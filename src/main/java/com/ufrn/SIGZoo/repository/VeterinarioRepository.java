package com.ufrn.SIGZoo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    
    boolean existsByCrmv(String crmv);
    Optional<Veterinario> findByCrmv(String crmv);

    @Query("SELECT v FROM Veterinario v ORDER BY SIZE(v.pacientes) DESC")
    List<Veterinario> findAllByOrderByQtdPacientesDesc();
}
