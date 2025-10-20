package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;

import com.ufrn.SIGZoo.model.entity.Animal;

public class AnimalDTO {

    private Integer id;

    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String origem;
    
    private String veterinarioNome;
    private Integer veterinarioId;
    // private Integer especieId; 
    // private Integer recintoId;

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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getVeterinarioNome() {
        return veterinarioNome;
    }

    public void setVeterinarioNome(String veterinarioNome) {
        this.veterinarioNome = veterinarioNome;
    }

    public Integer getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Integer veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Animal toEntity() {
        Animal animal = new Animal();
        
        animal.setNome(this.getNome());
        animal.setSexo(this.getSexo());
        animal.setNascimento(this.getNascimento());
        animal.setOrigem(this.getOrigem());

        return animal;
    }
}
