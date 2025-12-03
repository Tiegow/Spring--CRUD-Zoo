package com.ufrn.SIGZoo.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.SIGZoo.model.entity.Funcionario;
import com.ufrn.SIGZoo.model.entity.OrdemServico;

public class OrdemServicoDTO {

    private Integer id;
    private String descricao;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private String local;

    private List<Integer> funcionariosIds = new ArrayList<>();

    private List<FuncionarioDTO> funcionarios = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDate dataConclusao) { this.dataConclusao = dataConclusao; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public List<Integer> getFuncionariosIds() { return funcionariosIds; }
    public void setFuncionariosIds(List<Integer> funcionariosIds) { this.funcionariosIds = funcionariosIds; }

    public List<FuncionarioDTO> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(List<FuncionarioDTO> funcionarios) { this.funcionarios = funcionarios; }

    // DTO → ENTITY 

    public OrdemServico toEntity() {
        OrdemServico os = new OrdemServico();

        os.setId(this.id);
        os.setDescricao(this.descricao);
        os.setStatus(this.status);
        os.setDataInicio(this.dataInicio);
        os.setDataConclusao(this.dataConclusao);
        os.setLocal(this.local);
        return os;
    }

    // ENTITY → DTO 

    public static OrdemServicoDTO fromEntity(OrdemServico os) {
        if (os == null) return null;

        OrdemServicoDTO dto = new OrdemServicoDTO();

        dto.setId(os.getId());
        dto.setDescricao(os.getDescricao());
        dto.setStatus(os.getStatus());
        dto.setDataInicio(os.getDataInicio());
        dto.setDataConclusao(os.getDataConclusao());
        dto.setLocal(os.getLocal());

        // Converte funcionários para DTO simples
        List<FuncionarioDTO> lista = new ArrayList<>();

        if (os.getFuncionarios() != null) {
            for (Funcionario f : os.getFuncionarios()) {
                FuncionarioDTO fd = new FuncionarioDTO();
                fd.setId(f.getId());
                fd.setNome(f.getNome());
                fd.setCargo(f.getCargo());
                lista.add(fd);
            }
        }

        dto.setFuncionarios(lista);

        return dto;
    }
}
