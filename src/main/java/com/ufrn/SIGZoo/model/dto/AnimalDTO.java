package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;

import com.ufrn.SIGZoo.model.entity.Animal;

public class AnimalDTO {

    private Integer id;

    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String origem;

    private Integer especieId;
    private String especieNome;

    private Integer recintoId;
    private String recintoNome;

    private Integer veterinarioId;
    private String veterinarioNome;

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

    public Integer getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Integer especieId) {
        this.especieId = especieId;
    }

    public String getEspecieNome() {
        return especieNome;
    }

    public void setEspecieNome(String especieNome) {
        this.especieNome = especieNome;
    }

    public Integer getRecintoId() {
        return recintoId;
    }

    public void setRecintoId(Integer recintoId) {
        this.recintoId = recintoId;
    }

    public String getRecintoNome() {
        return recintoNome;
    }

    public void setRecintoNome(String recintoNome) {
        this.recintoNome = recintoNome;
    }

    public Integer getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Integer veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public String getVeterinarioNome() {
        return veterinarioNome;
    }

    public void setVeterinarioNome(String veterinarioNome) {
        this.veterinarioNome = veterinarioNome;
    }

    public Animal toEntity() {
        Animal animal = new Animal();
        animal.setId(this.id);
        animal.setNome(this.nome);
        animal.setSexo(this.sexo);
        animal.setNascimento(this.nascimento);
        animal.setOrigem(this.origem);
        return animal;
    }

    public static AnimalDTO fromEntity(Animal animal) {
        if (animal == null) {
            return null;
        }
        AnimalDTO dto = new AnimalDTO();
        dto.setId(animal.getId());
        dto.setNome(animal.getNome());
        dto.setSexo(animal.getSexo());
        dto.setNascimento(animal.getNascimento());
        dto.setOrigem(animal.getOrigem());

        if (animal.getEspecie() != null) {
            dto.setEspecieId(animal.getEspecie().getId());
            dto.setEspecieNome(animal.getEspecie().getNome());
        }

        if (animal.getRecinto() != null) {
            dto.setRecintoId(animal.getRecinto().getId());
            dto.setRecintoNome(animal.getRecinto().getNome());
        }

        if (animal.getVeterinario() != null) {
            dto.setVeterinarioId(animal.getVeterinario().getId());
            dto.setVeterinarioNome(animal.getVeterinario().getNome());
        }

        return dto;
    }
}
