package com.ufrn.SIGZoo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlanoDietaRecinto {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantidadeCarne;

    @Column
    private Integer quantidadeVegetais;

    public PlanoDietaRecinto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeCarne() {
        return quantidadeCarne;
    }

    public void setQuantidadeCarne(Integer quantidadeCarne) {
        this.quantidadeCarne = quantidadeCarne;
    }

    public Integer getQuantidadeVegetais() {
        return quantidadeVegetais;
    }

    public void setQuantidadeVegetais(Integer quantidadeVegetais) {
        this.quantidadeVegetais = quantidadeVegetais;
    }
}
