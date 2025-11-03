package com.ufrn.SIGZoo.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String areaHabitavel;

    @Column
    private String status;

    // Um plano de dieta pode estar em vários recintos
    @ManyToOne
    @JoinColumn(name = "plano_dieta_id")
    private PlanoDieta planoDieta;

    // Um recinto pode ter vários animais
    @OneToMany(mappedBy = "recinto")
    private List<Animal> animais;

    // Um recinto pode ter vários tratadores, e um tratador pode cuidar de vários recintos
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

    public String getAreaHabitavel() {
        return areaHabitavel;
    }

    public void setAreaHabitavel(String areaHabitavel) {
        this.areaHabitavel = areaHabitavel;
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
