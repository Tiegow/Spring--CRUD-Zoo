package com.ufrn.SIGZoo.model.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sexo;

<<<<<<< Updated upstream
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especie_id")
    private Especie especie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recinto_id")
    @JsonBackReference 
=======
    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especie especie;
    
    @ManyToOne
    @JoinColumn(name = "recinto_id")
>>>>>>> Stashed changes
    private Recinto recinto;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY) // Carrega o veterinario apenas quando for acessado
    @JoinColumn(name = "veterinario_id") 
    private Veterinario veterinario;

    private LocalDate nascimento;
    private String origem;

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
    public LocalDate getNascimento() {
        return nascimento;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Especie getEspecie() {
        return especie;
    }
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    public Recinto getRecinto() {
        return recinto;
    }
    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }
    public Veterinario getVeterinario() {
        return veterinario;
    }
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
    public Especie getEspecie() {
        return especie;
    }
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    public Recinto getRecinto() {
        return recinto;
    }
    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }
}
