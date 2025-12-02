package com.ufrn.SIGZoo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.repository.FuncionarioRepository;
import com.ufrn.SIGZoo.repository.OrdemServicoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // ============================================================
    // CRUD
    // ============================================================

    @Transactional
    public OrdemServicoDTO criar(OrdemServicoDTO dto) {
        OrdemServico os = dto.toEntity();

        setFuncionariosFromDto(os, dto);

        OrdemServico salvo = ordemServicoRepository.save(os);
        return OrdemServicoDTO.fromEntity(salvo);
    }

    @Transactional
    public OrdemServicoDTO atualizar(Integer id, OrdemServicoDTO dto) {
        OrdemServico existente = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada."));

        existente.setDescricao(dto.getDescricao());
        existente.setStatus(dto.getStatus());
        existente.setDataInicio(dto.getDataInicio());
        existente.setDataConclusao(dto.getDataConclusao());
        existente.setLocal(dto.getLocal());

        setFuncionariosFromDto(existente, dto);

        OrdemServico atualizado = ordemServicoRepository.save(existente);
        return OrdemServicoDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Integer id) {
        OrdemServico os = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada."));

        ordemServicoRepository.delete(os);
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Integer id) {
        OrdemServico os = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de Serviço não encontrada."));
        return OrdemServicoDTO.fromEntity(os);
    }

    @Transactional(readOnly = true)
    public Page<OrdemServicoDTO> listarTodos(Pageable pageable) {
        Page<OrdemServico> page = ordemServicoRepository.findAll(pageable);
        return page.map(OrdemServicoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarTodosList() {
        return ordemServicoRepository.findAll()
                .stream()
                .map(OrdemServicoDTO::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> buscarPorFuncionario(Integer funcionarioId) {
        Funcionario func = funcionarioRepository.findById(funcionarioId)
            .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        List<OrdemServico> ordens = ordemServicoRepository.findByFuncionariosContaining(func);

        return ordens.stream()
                .map(OrdemServicoDTO::fromEntity)
                .collect(Collectors.toList());
    }



    // FILTROS ESPECIAIS
    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarPorFuncionario(Integer funcionarioId) {
        Funcionario func = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        List<OrdemServico> ordens = ordemServicoRepository.findByFuncionariosContaining(func);

        return ordens.stream()
                .map(OrdemServicoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> buscarPorStatus(String status) {
        List<OrdemServico> ordens = ordemServicoRepository.findByStatus(status);
        return ordens.stream().map(OrdemServicoDTO::fromEntity).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> buscarPorIntervaloDeData(LocalDate dataInicio, LocalDate dataFim) {

        Date inicioDate = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fimDate = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<OrdemServico> ordens = ordemServicoRepository
            .findByDataInicioGreaterThanEqualAndDataConclusaoLessThanEqual(inicioDate, fimDate);

        return ordens.stream().map(OrdemServicoDTO::fromEntity).collect(Collectors.toList());
    }

    private void setFuncionariosFromDto(OrdemServico os, OrdemServicoDTO dto) {
        List<Funcionario> lista = new ArrayList<>();

        // IDs de tratadores
        if (dto.getTratadores() != null) {
            dto.getTratadores().forEach(t -> {
                Funcionario func = funcionarioRepository.findById(t.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Tratador ID " + t.getId() + " não encontrado."));
                lista.add(func);
            });
        }

        // IDs de veterinários
        if (dto.getVeterinarios() != null) {
            dto.getVeterinarios().forEach(v -> {
                Funcionario func = funcionarioRepository.findById(v.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Veterinário ID " + v.getId() + " não encontrado."));
                lista.add(func);
            });
        }

        os.setFuncionarios(lista);
    }
}
