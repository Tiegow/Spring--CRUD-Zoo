package com.ufrn.SIGZoo.model.dto;

import java.util.List;

import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;


public class RecintoDTO {
    
    private Integer id;

    private List<Tratador> tratadores;

    //TODO: Evento eventoAtual
    //TODO: PlanoDieta planoDieta
    //TODO: List<Animal> animais

    private String nome;
    private float areaHabitavel;
    private String tipo;
    private String status;

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
    public float getAreaHabitavel() {
        return areaHabitavel;
    }
    public void setAreaHabitavel(float areaHabitavel) {
        this.areaHabitavel = areaHabitavel;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public Recinto toEntity(){
        Recinto recinto = new Recinto();

        recinto.setId(this.getId());
        recinto.setAreaHabitavel(this.getAreaHabitavel());
        recinto.setNome(this.getNome());
        recinto.setStatus(this.getStatus());
        recinto.setTipo(this.getTipo());

        return recinto;

    }
}
