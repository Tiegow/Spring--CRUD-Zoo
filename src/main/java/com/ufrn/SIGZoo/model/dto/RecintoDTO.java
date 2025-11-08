package com.ufrn.SIGZoo.model.dto;

import java.util.List;

import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import java.util.stream.Collectors;

public class RecintoDTO {

    private Integer id;
    private String nome;
    private float areaHabitavel;
    private String status;
    
    private int populacao;
    
    private Integer planoDietaId;
    private List<Integer> tratadorIds; 

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
    public float getAreaHabitavel() {
        return areaHabitavel;
    }
    public void setAreaHabitavel(float areaHabitavel) {
        this.areaHabitavel = areaHabitavel;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getPopulacao() {
        return populacao;
    }
    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }
    public Integer getPlanoDietaId() {
        return planoDietaId;
    }
    public void setPlanoDietaId(Integer planoDietaId) {
        this.planoDietaId = planoDietaId;
    }
    public List<Integer> getTratadorIds() {
        return tratadorIds;
    }
    public void setTratadorIds(List<Integer> tratadorIds) {
        this.tratadorIds = tratadorIds;
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
            dto.setPlanoDietaId(recinto.getPlanoDieta().getId());
        }

        if (recinto.getTratadores() != null) {
            dto.setTratadorIds(recinto.getTratadores().stream()
                    .map(Tratador::getId) 
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
