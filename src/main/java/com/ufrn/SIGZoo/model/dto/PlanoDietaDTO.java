package com.ufrn.SIGZoo.model.dto;

import com.ufrn.SIGZoo.model.entity.PlanoDieta;

public class PlanoDietaDTO {

    private Integer id;

    private Integer quantidadeCarne;

    private Integer quantidadeVegetais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeCarne() {
        return quantidadeCarne;
    }

    public void setQuantidadeCarne(Integer quantidadeCarne) {
        this.quantidadeCarne = quantidadeCarne;
    }

    public Integer getQuantidadeVegetais() {
        return quantidadeVegetais;
    }

    public void setQuantidadeVegetais(Integer quantidadeVegetais) {
        this.quantidadeVegetais = quantidadeVegetais;
    }

    public PlanoDieta toEntity() {
        PlanoDieta plano = new PlanoDieta();
        plano.setId(this.id); 
        plano.setQuantidadeCarne(this.quantidadeCarne);
        plano.setQuantidadeVegetais(this.quantidadeVegetais);
        return plano;
    }

    public static PlanoDietaDTO fromEntity(PlanoDieta plano) {
        if (plano == null) {
            return null;
        }
        PlanoDietaDTO dto = new PlanoDietaDTO();
        dto.setId(plano.getId());
        dto.setQuantidadeCarne(plano.getQuantidadeCarne());
        dto.setQuantidadeVegetais(plano.getQuantidadeVegetais());
        return dto;
    }
}
