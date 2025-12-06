package com.ufrn.SIGZoo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Ingresso;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {

    // --- FILTRO POR PREÇO ---
    List<Ingresso> findByCusto(double custo);

    List<Ingresso> findByCustoGreaterThan(double valor);

    List<Ingresso> findByCustoLessThan(double valor);

    List<Ingresso> findByCustoBetween(double min, double max);

    // Versões paginadas
    Page<Ingresso> findByCusto(double custo, Pageable pageable);

    Page<Ingresso> findByCustoGreaterThan(double valor, Pageable pageable);

    Page<Ingresso> findByCustoLessThan(double valor, Pageable pageable);

    Page<Ingresso> findByCustoBetween(double min, double max, Pageable pageable);


    // --- FILTRO POR DATA DE COMPRA ---
    List<Ingresso> findByDataCompraBetween(LocalDate inicio, LocalDate fim);

    Page<Ingresso> findByDataCompraBetween(LocalDate inicio, LocalDate fim, Pageable pageable);


    // --- FILTRO POR DATA DE VISITA ---
    List<Ingresso> findByDataVisitaBetween(LocalDate inicio, LocalDate fim);

    Page<Ingresso> findByDataVisitaBetween(LocalDate inicio, LocalDate fim, Pageable pageable);
}
