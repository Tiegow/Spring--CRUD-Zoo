package com.ufrn.SIGZoo.repository;

import java.util.Optional;
import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    Optional<Evento> findById(Integer id);
    Optional<Evento> findByNome(String nome);

    List<Evento> findByTipo(String tipo);

    Page<Evento> findByCapacidadeBetween(Integer min, Integer max, Pageable pageable);
    Page<Evento> findByCapacidadeGreaterThanEqual(Integer capacidade, Pageable pageable);
    Page<Evento> findByCapacidadeLessThanEqual(Integer capacidade, Pageable pageable);

    List<Evento> findByData(Date data);

    Page<Evento> findAllByOrderByData(Pageable pageable);
}
