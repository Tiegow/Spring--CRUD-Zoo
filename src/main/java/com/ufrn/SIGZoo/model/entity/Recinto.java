package com.ufrn.SIGZoo.model.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private float areaHabitavel;

    @Column
    private Integer populacao;

    @Column
    private String tipo;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "plano_dieta_id")
    private PlanoDieta planoDieta;

    @OneToMany(mappedBy = "recinto")
    @JsonManagedReference
    private List<Animal> animais;

    @ManyToMany
    @JoinTable(
        name = "recinto_tratador",
        joinColumns = @JoinColumn(name = "recinto_id"),
        inverseJoinColumns = @JoinColumn(name = "tratador_id")
    )
    private List<Tratador> tratadores;

    public Recinto() {}

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getAreaHabitavel() {
        return areaHabitavel;
    }

    public void setAreaHabitavel(Float areaHabitavel) {
        this.areaHabitavel = areaHabitavel;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlanoDieta getPlanoDieta() {
        return planoDieta;
    }

    public void setPlanoDieta(PlanoDieta planoDieta) {
        this.planoDieta = planoDieta;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public List<Tratador> getTratadores() {
        return tratadores;
    }

    public void setTratadores(List<Tratador> tratadores) {
        this.tratadores = tratadores;
    }
}
