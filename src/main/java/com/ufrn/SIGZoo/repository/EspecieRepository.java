package com.ufrn.SIGZoo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer>{
    Optional<Especie> findById(Integer id);
    Optional<Especie> findByNome(String nome);
    Optional<Especie> findByExpectativaVida(float expectativaVida);

    List<Especie> findAllByOrderByNome();

    List<Especie> findByTamanhoMaximoGrupoLessThan(Integer tamanhoMaximoGrupo);
    List<Especie> findByTamanhoMinimoGrupoGreaterThan(Integer tamanhoMaximoGrupo);

}
