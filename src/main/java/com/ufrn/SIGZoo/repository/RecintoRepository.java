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

    boolean existsByPlanoDietaId(Integer planoDietaId);

    Optional<Recinto> findById(Integer id);
    Optional<Recinto> findByNome(String nome);

    List<Recinto> findByStatus(String status);
    List<Recinto> findByTipo(String tipo);

    Page<Recinto> findAllByOrderByNome(Pageable pageable);


    List<Recinto> findByPopulacao(Integer populacao);

    List<Recinto> findByPopulacaoGreaterThanEqual(Integer minimo);
    List<Recinto> findByPopulacaoLessThanEqual(Integer maximo);

    List<Recinto> findByPopulacaoBetween(Integer minimo, Integer maximo);

    
    List<Recinto> findByAreaHabitavel(Float area);

    List<Recinto> findByAreaHabitavelGreaterThanEqual(Float minimo);
    List<Recinto> findByAreaHabitavelLessThanEqual(Float maximo);

    List<Recinto> findByAreaHabitavelBetween(Float minimo, Float maximo);
}
