package com.ufrn.SIGZoo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "recintosAtribuidos")
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
}
