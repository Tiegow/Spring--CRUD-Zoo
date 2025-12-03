package com.ufrn.SIGZoo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    List<OrdemServico> findByStatus(String status);
    Page<OrdemServico> findByStatus(String status, Pageable pageable);

    // Intervalo de datas (LIST)
    List<OrdemServico> findByDataInicioGreaterThanEqualAndDataConclusaoLessThanEqual(
            LocalDate inicio,
            LocalDate fim
    );

    // Intervalo de datas (PAGE)
    Page<OrdemServico> findByDataInicioGreaterThanEqualAndDataConclusaoLessThanEqual(
            LocalDate inicio,
            LocalDate fim,
            Pageable pageable
    );
}

