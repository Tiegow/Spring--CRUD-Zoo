package com.ufrn.SIGZoo.model.dto;

import java.sql.Date;
import java.util.List;

import com.ufrn.SIGZoo.model.entity.Evento;

public class EventoDTO {

    private Integer id;
    private String nome;
    private Date data;
    private Integer capacidade;
    private String tipo;

    // IDs dos recintos associados ao evento
    private List<Integer> recintosIds;

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

    public List<Integer> getRecintosIds() {
        return recintosIds;
    }

    public void setRecintosIds(List<Integer> recintosIds) {
        this.recintosIds = recintosIds;
    }

    public Evento toEntity(){
        Evento evento = new Evento();

        evento.setId(this.getId());
        evento.setNome(this.getNome());
        evento.setData(this.getData());
        evento.setCapacidade(this.getCapacidade());
        evento.setTipo(this.getTipo());
        
        return evento;
    }
}
