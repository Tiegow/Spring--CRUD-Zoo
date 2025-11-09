package com.ufrn.SIGZoo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.PlanoDieta;

@Repository
public interface PlanoDietaRepository extends JpaRepository<PlanoDieta, Integer> {

    Optional<PlanoDieta> findById(Integer id);

    List<PlanoDieta> findByQuantidadeCarneGreaterThan(Integer quantidadeCarne);
    List<PlanoDieta> findByQuantidadeCarneLessThan(Integer quantidadeCarne);

    List<PlanoDieta> findByQuantidadeVegetaisGreaterThan(Integer quantidadeVegetais);
    List<PlanoDieta> findByQuantidadeVegetaisLessThan(Integer quantidadeVegetais);
}
