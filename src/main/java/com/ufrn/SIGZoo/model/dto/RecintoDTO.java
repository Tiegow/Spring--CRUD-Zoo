package com.ufrn.SIGZoo.model.dto;

import java.util.List;

import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import java.util.stream.Collectors;

public class RecintoDTO {

    private Integer id;

    private String nome;
    private float areaHabitavel;
    private Integer populacao;
    private String status;
        
    private List<Integer> tratadorIds; 
    private List<Integer> animaisIds;

    private PlanoDietaDTO planoDieta;

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
    public Float getAreaHabitavel() {
        return areaHabitavel;
    }
    public void setAreaHabitavel(Float areaHabitavel) {
        this.areaHabitavel = areaHabitavel;
    }
    public Integer getPopulacao() {
        return populacao;
    }
    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Integer> getTratadorIds() {
        return tratadorIds;
    }
    public void setTratadorIds(List<Integer> tratadorIds) {
        this.tratadorIds = tratadorIds;
    }
    public List<Integer> getAnimaisIds() {
        return animaisIds;
    }
    public void setAnimaisIds(List<Integer> animaisIds) {
        this.animaisIds = animaisIds;
    }
    public PlanoDietaDTO getPlanoDieta() {
        return planoDieta;
    }
    public void setPlanoDieta(PlanoDietaDTO planoDieta) {
        this.planoDieta = planoDieta;
    }    

    public Recinto toEntity() {
        Recinto recinto = new Recinto();
        recinto.setId(this.getId()); 
        recinto.setAreaHabitavel(this.getAreaHabitavel());
        recinto.setNome(this.getNome());
        recinto.setStatus(this.getStatus());
        
        return recinto;
    }


    public static RecintoDTO fromEntity(Recinto recinto) {
        RecintoDTO dto = new RecintoDTO();
        dto.setId(recinto.getId());
        dto.setNome(recinto.getNome());
        dto.setAreaHabitavel(recinto.getAreaHabitavel());
        dto.setStatus(recinto.getStatus());

        if (recinto.getAnimais() != null) {
            dto.setPopulacao(recinto.getAnimais().size());
        } else {
            dto.setPopulacao(0);
        }

        if (recinto.getPlanoDieta() != null) {
            dto.setPlanoDieta(PlanoDietaDTO.fromEntity(recinto.getPlanoDieta()));
        }

        if (recinto.getAnimais() != null) {
            dto.setAnimaisIds(
                recinto.getAnimais()
                    .stream()
                    .map(a -> a.getId())
                    .collect(Collectors.toList())
            );
        }

        if (recinto.getTratadores() != null) {
            dto.setTratadorIds(recinto.getTratadores().stream()
                    .map(Tratador::getId) 
                    .collect(Collectors.toList()));
        }

        return dto;
    }

}
