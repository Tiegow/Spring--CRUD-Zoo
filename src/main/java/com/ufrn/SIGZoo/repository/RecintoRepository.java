package com.ufrn.SIGZoo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Recinto;

@Repository
public interface RecintoRepository extends JpaRepository<Recinto, Integer> {
<<<<<<< Updated upstream
    
    boolean existsByPlanoDietaId(Integer planoDietaId);
=======

    Optional<Recinto> findById(Integer id);
    Optional<Recinto> findByNome(String nome);

    List<Recinto> findByStatus(String status);
    List<Recinto> findByTipo(String tipo);

    Page<Recinto> findAllByOrderByNome(Pageable pageable);
>>>>>>> Stashed changes
}
