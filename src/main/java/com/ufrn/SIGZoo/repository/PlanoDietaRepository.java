package com.ufrn.SIGZoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.PlanoDieta;

@Repository
public interface PlanoDietaRepository extends JpaRepository<PlanoDieta, Integer> {

    
}
