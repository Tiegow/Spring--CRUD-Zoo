package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;

import com.ufrn.SIGZoo.model.entity.Tratador;

public class TratadorDTO {
    private Integer id;
    private String nome;
    private LocalDate nascimento;
    private LocalDate dataIngresso;
    private String cpf;
    private String especializacao;
    private float remuneracao; 
    private String turno;
    private int qtdRecintosAtribuidos;
    private String cargo = "Tratador";

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
    public LocalDate getDataIngresso() {
        return dataIngresso;
    }
    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEspecializacao() {
        return especializacao;
    }
    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
    public float getRemuneracao() {
        return remuneracao;
    }
    public void setRemuneracao(float remuneracao) {
        this.remuneracao = remuneracao;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public int getQtdRecintosAtribuidos() {
        return qtdRecintosAtribuidos;
    }
    public void setQtdRecintosAtribuidos(int qtdRecintosAtribuidos) {
        this.qtdRecintosAtribuidos = qtdRecintosAtribuidos;
    }
    public String getCargo() {
        return cargo;
    }

    public Tratador toEntity() {
        Tratador trat = new Tratador();

        trat.setNome(nome);
        trat.setCpf(cpf);
        trat.setEspecializacao(especializacao);
        trat.setNascimento(nascimento);
        trat.setRemuneracao(remuneracao);
        trat.setDataIngresso(dataIngresso);
        trat.setQtdRecintosAtribuidos(qtdRecintosAtribuidos);
        trat.setTurno(turno);

        return trat;
    }
}
