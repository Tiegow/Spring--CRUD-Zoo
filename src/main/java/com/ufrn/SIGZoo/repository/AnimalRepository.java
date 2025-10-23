package com.ufrn.SIGZoo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Page<Animal> findAll(Pageable pageable);

    List<Animal> findAllByVeterinarioId(Integer veterinarioId);

    @Query("SELECT a FROM Animal a WHERE UPPER(TRIM(a.sexo)) = UPPER(TRIM(:sexo))")
    List<Animal> findAllBySexo(@Param("sexo") String sexo);
}
