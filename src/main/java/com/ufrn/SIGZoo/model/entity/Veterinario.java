package com.ufrn.SIGZoo.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Veterinario extends Funcionario {

    @Column(unique = true, nullable = false)
    private String crmv;

    @JsonBackReference
    @OneToMany(mappedBy = "veterinario") // Relacionamento bidirecional
    private List<Animal> pacientes;

    public String getCrmv() {
        return crmv;
    }
    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }
    public List<Animal> getPacientes() {
        return pacientes;
    }
    public void setPacientes(List<Animal> pacientes) {
        this.pacientes = pacientes;
    }

    public int getQtdPacientes() {
        return this.pacientes.size();
    }

    @Override
    public String getCargo() {
        return "Veterinario";
    }    

    @PreRemove
    private void preRemove() {
        for (Animal animal : pacientes) {
            animal.setVeterinario(null);
        }
    }
}
