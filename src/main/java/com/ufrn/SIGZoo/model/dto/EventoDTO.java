package com.ufrn.SIGZoo.model.dto;

import java.sql.Date;

import com.ufrn.SIGZoo.model.entity.Evento;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EventoDTO {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private Date data;

    @Column
    private Integer capacidade;

    @Column
    private String tipo;

    public EventoDTO() {}

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Evento toEntity(){
        Evento evento = new Evento();

        evento.setCapacidade(this.getCapacidade());
        evento.setData(this.getData());
        evento.setId(this.getId());
        evento.setNome(this.getNome());
        evento.setTipo(this.getTipo());

        return evento;
    }
}
