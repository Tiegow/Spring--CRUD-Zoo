package com.ufrn.SIGZoo.model.dto;

import com.ufrn.SIGZoo.model.entity.Especie;

public class EspecieDTO {
    
    private Integer id;

    private String nome;

    private float expectativaVida;

    private String areaAdequada;
    
    private Integer tamanhoMinimoGrupo;
    
    private Integer tamanhoMaximoGrupo;


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

    public Especie toEntity(){
        Especie especie = new Especie();

        especie.setNome(this.getNome());
        especie.setExpectativaVida(this.getExpectativaVida());
        especie.setAreaAdequada(this.getAreaAdequada());
        especie.setTamanhoMinimoGrupo(this.getTamanhoMinimoGrupo());
        especie.setTamanhoMaximoGrupo(this.getTamanhoMaximoGrupo());
        
        return especie;

    }
}
