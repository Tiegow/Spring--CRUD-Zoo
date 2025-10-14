package com.ufrn.SIGZoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.Tratador;

@Repository
public interface TratadorRepository extends JpaRepository<Tratador, Integer> {

    List<Tratador> findByQtdRecintosAtribuidos(int qtdRecintosAtribuidos);
    List<Tratador> findByTurno(String turno);
}
