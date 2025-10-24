package com.ufrn.SIGZoo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private float expectativaVida;

    @Column
    private String areaAdequada;

    @Column
    private Integer tamanhoMinimoGrupo;
    
    @Column
    private Integer tamanhoMaximoGrupo;

    public Especie() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getExpectativaVida() {
        return expectativaVida;
    }

    public void setExpectativaVida(float expectativaVida) {
        this.expectativaVida = expectativaVida;
    }

    public String getAreaAdequada() {
        return areaAdequada;
    }

    public void setAreaAdequada(String areaAdequada) {
        this.areaAdequada = areaAdequada;
    }

    public Integer getTamanhoMinimoGrupo() {
        return tamanhoMinimoGrupo;
    }

    public void setTamanhoMinimoGrupo(Integer tamanhoMinimoGrupo) {
        this.tamanhoMinimoGrupo = tamanhoMinimoGrupo;
    }

    public Integer getTamanhoMaximoGrupo() {
        return tamanhoMaximoGrupo;
    }

    public void setTamanhoMaximoGrupo(Integer tamanhoMaximoGrupo) {
        this.tamanhoMaximoGrupo = tamanhoMaximoGrupo;
    }

}
