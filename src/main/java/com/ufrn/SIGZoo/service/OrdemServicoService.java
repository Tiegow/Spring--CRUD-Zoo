package com.ufrn.SIGZoo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.dto.OrdemServicoDTO;
import com.ufrn.SIGZoo.model.entity.Funcionario;
import com.ufrn.SIGZoo.model.entity.OrdemServico;
import com.ufrn.SIGZoo.repository.FuncionarioRepository;
import com.ufrn.SIGZoo.repository.OrdemServicoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private OrdemServicoDTO toDTO(OrdemServico os) {
        return OrdemServicoDTO.fromEntity(os);
    }

    // Criar
    @Transactional
    public OrdemServicoDTO criar(OrdemServicoDTO dto) {

        OrdemServico os = new OrdemServico();
        os.setDescricao(dto.getDescricao());
        os.setStatus(dto.getStatus());
        os.setLocal(dto.getLocal());
        os.setDataInicio(dto.getDataInicio());
        os.setDataConclusao(dto.getDataConclusao());

        List<Funcionario> funcionarios = dto.getFuncionariosIds()
                .stream()
                .map(id -> funcionarioRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Funcionário ID " + id + " não encontrado.")))
                .collect(Collectors.toList());

        os.setFuncionarios(funcionarios);

        return toDTO(ordemServicoRepository.save(os));
    }

    // Atualizar
    @Transactional
    public OrdemServicoDTO atualizar(Integer id, OrdemServicoDTO dto) {

        OrdemServico os = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada."));

        os.setDescricao(dto.getDescricao());
        os.setStatus(dto.getStatus());
        os.setDataInicio(dto.getDataInicio());
        os.setDataConclusao(dto.getDataConclusao());
        os.setLocal(dto.getLocal());

        if (dto.getFuncionariosIds() != null) {
            List<Funcionario> funcionarios = dto.getFuncionariosIds()
                    .stream()
                    .map(fid -> funcionarioRepository.findById(fid)
                            .orElseThrow(() -> new EntityNotFoundException("Funcionário ID " + fid + " não encontrado.")))
                    .collect(Collectors.toList());

            os.setFuncionarios(funcionarios);
        }

        return toDTO(ordemServicoRepository.save(os));
    }

    // Deletar
    @Transactional
    public void deletar(Integer id) {
        OrdemServico os = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada."));
        ordemServicoRepository.delete(os);
    }

    // Buscar por ID
    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Integer id) {
        OrdemServico os = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada."));
        return toDTO(os);
    }

    // Listar paginado
    @Transactional(readOnly = true)
    public Page<OrdemServicoDTO> listarTodos(Pageable pageable) {
        return ordemServicoRepository.findAll(pageable)
                .map(this::toDTO);
    }

    // Buscar por status - LIST
    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> buscarPorStatus(String status) {
        return ordemServicoRepository.findByStatus(status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por status - PAGINADO
    @Transactional(readOnly = true)
    public Page<OrdemServicoDTO> listarPorStatusPaginado(String status, Pageable pageable) {
        return ordemServicoRepository.findByStatus(status, pageable)
                .map(this::toDTO);
    }

    // FILTRO POR INTERVALO DE DATAS
    public Page<OrdemServicoDTO> listarPorPeriodoPaginado(LocalDate inicio, LocalDate fim, Pageable pageable) {
        return ordemServicoRepository
                .findByDataInicioGreaterThanEqualAndDataConclusaoLessThanEqual(inicio, fim, pageable)
                .map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public Long obterQtdOrdensServico() {
        return ordemServicoRepository.count();
    }
}
