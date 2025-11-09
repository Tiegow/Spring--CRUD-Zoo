package com.ufrn.SIGZoo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Recinto;

@Repository
public interface RecintoRepository extends JpaRepository<Recinto, Integer> {

    boolean existsByPlanoDietaId(Integer planoDietaId);

    Optional<Recinto> findById(Integer id);
    Optional<Recinto> findByNome(String nome);

    List<Recinto> findByStatus(String status);

    Page<Recinto> findAllByOrderByNome(Pageable pageable);

    @Query("SELECT r FROM Recinto r WHERE SIZE(r.animais) BETWEEN :minimo AND :maximo")
    List<Recinto> findByPopulacaoBetween(@Param("minimo") Integer minimo, @Param("maximo") Integer maximo);

    @Query("SELECT r FROM Recinto r WHERE SIZE(r.animais) >= :minimo")
    List<Recinto> findByPopulacaoGreaterThanEqual(@Param("minimo") Integer minimo);

    @Query("SELECT r FROM Recinto r WHERE SIZE(r.animais) <= :maximo")
    List<Recinto> findByPopulacaoLessThanEqual(@Param("maximo") Integer maximo);    
    
    List<Recinto> findByAreaHabitavel(Float area);

    List<Recinto> findByAreaHabitavelGreaterThanEqual(Float minimo);
    List<Recinto> findByAreaHabitavelLessThanEqual(Float maximo);

    List<Recinto> findByAreaHabitavelBetween(Float minimo, Float maximo);
}
