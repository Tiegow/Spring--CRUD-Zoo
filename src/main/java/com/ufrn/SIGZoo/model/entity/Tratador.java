package com.ufrn.SIGZoo.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Tratador extends Funcionario {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tratador_recinto",
        joinColumns = @JoinColumn(name = "tratador_id"),
        inverseJoinColumns = @JoinColumn(name = "recinto_id")
    )
    private List<Recinto> recintosAtribuidos;

    private int qtdRecintosAtribuidos = 0;
    private String turno;

    public int getQtdRecintosAtribuidos() {
        return qtdRecintosAtribuidos;
    }
    public void setQtdRecintosAtribuidos(int qtdRecintosAtribuidos) {
        this.qtdRecintosAtribuidos = qtdRecintosAtribuidos;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public List<Recinto> getRecintosAtribuidos() {
        return recintosAtribuidos;
    }
    public void setRecintosAtribuidos(List<Recinto> recintosAtribuidos) {
        this.recintosAtribuidos = recintosAtribuidos;
    }

    public void incrementarRecintos() {
        this.qtdRecintosAtribuidos++;
    }
    public void decrementarRecintos() {
        if (this.qtdRecintosAtribuidos > 0) {
            this.qtdRecintosAtribuidos--;
        }
    }
}
