package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.SIGZoo.model.entity.Funcionario;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.model.entity.OrdemServico;

public class OrdemServicoDTO {

    private Integer id;
    private String descricao;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private String local;

    private List<TratadorDTO> tratadores = new ArrayList<>();
    private List<VeterinarioDTO> veterinarios = new ArrayList<>();

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<TratadorDTO> getTratadores() {
        return tratadores;
    }

    public void setTratadores(List<TratadorDTO> tratadores) {
        this.tratadores = tratadores;
    }

    public List<VeterinarioDTO> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(List<VeterinarioDTO> veterinarios) {
        this.veterinarios = veterinarios;
    }

    // DTO -> Entidade
    public OrdemServico toEntity() {
        OrdemServico os = new OrdemServico();
        os.setId(this.id);
        os.setDescricao(this.descricao);
        os.setStatus(this.status);
        os.setDataInicio(this.dataInicio);
        os.setDataConclusao(this.dataConclusao);
        os.setLocal(this.local);

        // Cria lista única de Funcionários, mas usando apenas ids
        List<Funcionario> lista = new ArrayList<>();

        for (TratadorDTO t : this.tratadores) {
            Tratador trat = new Tratador();
            trat.setId(t.getId());
            lista.add(trat);
        }

        for (VeterinarioDTO v : this.veterinarios) {
            Veterinario vet = new Veterinario();
            vet.setId(v.getId());
            lista.add(vet);
        }

        os.setFuncionarios(lista);
        return os;
    }

    // Entidade -> DTO
    public static OrdemServicoDTO fromEntity(OrdemServico os) {
        if (os == null) return null;

        OrdemServicoDTO dto = new OrdemServicoDTO();

        dto.setId(os.getId());
        dto.setDescricao(os.getDescricao());
        dto.setStatus(os.getStatus());
        dto.setDataInicio(os.getDataInicio());
        dto.setDataConclusao(os.getDataConclusao());
        dto.setLocal(os.getLocal());

        List<TratadorDTO> tratList = new ArrayList<>();
        List<VeterinarioDTO> vetList = new ArrayList<>();

        if (os.getFuncionarios() != null) {
            for (Funcionario f : os.getFuncionarios()) {

                if (f instanceof Tratador trat) {
                    TratadorDTO td = new TratadorDTO();
                    td.setId(trat.getId());
                    td.setNome(trat.getNome());
                    tratList.add(td);

                } else if (f instanceof Veterinario vet) {
                    VeterinarioDTO vd = new VeterinarioDTO();
                    vd.setId(vet.getId());
                    vd.setNome(vet.getNome());
                    vetList.add(vd);
                }
            }
        }

        dto.setTratadores(tratList);
        dto.setVeterinarios(vetList);

        return dto;
    }
}
