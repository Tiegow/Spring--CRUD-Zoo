package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ufrn.SIGZoo.model.entity.Ingresso;

public class IngressoDTO {

    private Integer idIngresso;
    private LocalDate dataCompra;
    private LocalTime horaCompra;
    private LocalDate dataVisita;
    private LocalTime horaVisita;
    private double custo;

    public Integer getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Integer idIngresso) {
        this.idIngresso = idIngresso;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalTime getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(LocalTime horaCompra) {
        this.horaCompra = horaCompra;
    }

    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    public LocalTime getHoraVisita() {
        return horaVisita;
    }

    public void setHoraVisita(LocalTime horaVisita) {
        this.horaVisita = horaVisita;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    // DTO → ENTITY
    public Ingresso toEntity() {
        Ingresso ingresso = new Ingresso();

        ingresso.setIdIngresso(this.idIngresso);
        ingresso.setDataCompra(this.dataCompra);
        ingresso.setHoraCompra(this.horaCompra);
        ingresso.setDataVisita(this.dataVisita);
        ingresso.setHoraVisita(this.horaVisita);
        ingresso.setCusto(this.custo);

        return ingresso;
    }

    // ENTITY → DTO
    public static IngressoDTO fromEntity(Ingresso ingresso) {
        if (ingresso == null) return null;

        IngressoDTO dto = new IngressoDTO();

        dto.setIdIngresso(ingresso.getIdIngresso());
        dto.setDataCompra(ingresso.getDataCompra());
        dto.setHoraCompra(ingresso.getHoraCompra());
        dto.setDataVisita(ingresso.getDataVisita());
        dto.setHoraVisita(ingresso.getHoraVisita());
        dto.setCusto(ingresso.getCusto());

        return dto;
    }
}
