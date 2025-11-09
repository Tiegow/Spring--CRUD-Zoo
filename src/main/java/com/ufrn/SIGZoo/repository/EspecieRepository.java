package com.ufrn.SIGZoo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer>{

    Optional<Especie> findById(Integer id);
    Optional<Especie> findByNome(String nome);

    Page<Especie> findAllByOrderByNome(Pageable pageable);

    // Filtro por expectativa de vida (range)
    List<Especie> findByExpectativaVidaBetween(Float min, Float max);

    // Filtro por tamanho de grupo (range)
    List<Especie> findByTamanhoMinimoGrupoGreaterThanEqualAndTamanhoMaximoGrupoLessThanEqual(
        Integer min, Integer max
    );
}

