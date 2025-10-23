package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;

import com.ufrn.SIGZoo.model.entity.Veterinario;

public class VeterinarioDTO {

    private Integer id;
    private String nome;
    private LocalDate nascimento;
    private LocalDate dataIngresso;
    private String cpf;
    private String especializacao;
    private float remuneracao; 
    private String crmv;
    private int qtdPacientes;
    private String cargo = "Veterinario";

    public String getNome() {
        return nome;
    }
    public int getQtdPacientes() {
        return qtdPacientes;
    }
    public void setQtdPacientes(int qtdPacientes) {
        this.qtdPacientes = qtdPacientes;
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
    public String getCrmv() {
        return crmv;
    }
    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }
    public LocalDate getDataIngresso() {
        return dataIngresso;
    }
    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }    
    public String getCargo() {
        return this.cargo;
    }

    public Veterinario toEntity() {
        Veterinario vet = new Veterinario();

        vet.setNome(nome);
        vet.setCpf(cpf);
        vet.setCrmv(crmv);
        vet.setEspecializacao(especializacao);
        vet.setNascimento(nascimento);
        vet.setRemuneracao(remuneracao);
        vet.setDataIngresso(dataIngresso);
        vet.setQtdPacientes(qtdPacientes);

        return vet;
    }

}
