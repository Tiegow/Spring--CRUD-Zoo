package com.ufrn.SIGZoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.Recinto;

@Repository
public interface RecintoRepository extends JpaRepository<Recinto, Integer> {

}
