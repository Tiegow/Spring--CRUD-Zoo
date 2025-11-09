package com.ufrn.SIGZoo.model.dto;

import java.util.List;

import com.ufrn.SIGZoo.model.entity.Recinto;
<<<<<<< Updated upstream
import com.ufrn.SIGZoo.model.entity.Tratador;
import java.util.stream.Collectors;
=======
>>>>>>> Stashed changes

public class RecintoDTO {

    private Integer id;
<<<<<<< Updated upstream
    private String nome;
    private float areaHabitavel;
=======

    private String nome;
    private String areaHabitavel;
    private String tipo;
>>>>>>> Stashed changes
    private String status;
    
    private int populacao;
    
    private Integer planoDietaId;
    private List<Integer> tratadorIds; 

    // Em vez das entidades completas, retornamos apenas IDs ou DTOs leves
    private Integer planoDietaId;
    private List<Integer> animaisIds;
    private List<Integer> tratadoresIds;

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
    public String getAreaHabitavel() {
        return areaHabitavel;
    }
    public void setAreaHabitavel(String areaHabitavel) {
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

<<<<<<< Updated upstream
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
=======
    public Integer getPlanoDietaId() {
        return planoDietaId;
    }
    public void setPlanoDietaId(Integer planoDietaId) {
        this.planoDietaId = planoDietaId;
    }

    public List<Integer> getAnimaisIds() {
        return animaisIds;
    }
    public void setAnimaisIds(List<Integer> animaisIds) {
        this.animaisIds = animaisIds;
    }

    public List<Integer> getTratadoresIds() {
        return tratadoresIds;
    }
    public void setTratadoresIds(List<Integer> tratadoresIds) {
        this.tratadoresIds = tratadoresIds;
    }

    public Recinto toEntity(){
        Recinto recinto = new Recinto();

        recinto.setId(this.getId());
        recinto.setNome(this.getNome());
        recinto.setAreaHabitavel(this.getAreaHabitavel());
        recinto.setTipo(this.getTipo());
        
        return recinto;
>>>>>>> Stashed changes
    }

}
