package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.model.entity.PlanoDieta;
import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.repository.PlanoDietaRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;
import com.ufrn.SIGZoo.repository.TratadorRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecintoService {

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private PlanoDietaRepository planoDietaRepository;

    @Autowired
    private TratadorRepository tratadorRepository;


    // DELETAR    
    @Transactional
    public void deletar(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto com ID " + id + " não encontrado!"));

        if (recinto.getAnimais() != null && !recinto.getAnimais().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível deletar um recinto que contém animais.");
        }

        recintoRepository.delete(recinto);
    }


    public long obterQtdRecintos() {
        return recintoRepository.count();
    }

    
    // CREATE    
    @Transactional
    public RecintoDTO criar(RecintoDTO dto) {
        Recinto recinto = toEntity(dto);
        recintoRepository.save(recinto);
        return toDTO(recinto);
    }

    
    // UPDATE    
    @Transactional
    public RecintoDTO atualizar(Integer id, RecintoDTO dto) {
        Recinto existente = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));

        existente.setNome(dto.getNome());
        existente.setTipo(dto.getTipo());
        existente.setStatus(dto.getStatus());
        existente.setAreaHabitavel(dto.getAreaHabitavel());
        existente.setPopulacao(dto.getPopulacao());

        // Plano de dieta
        if (dto.getPlanoDietaId() != null) {
            PlanoDieta plano = planoDietaRepository.findById(dto.getPlanoDietaId())
                    .orElseThrow(() -> new EntityNotFoundException("Plano de dieta não encontrado."));
            existente.setPlanoDieta(plano);
        } else {
            existente.setPlanoDieta(null);
        }

        // Tratadores
        if (dto.getTratadorIds() != null) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadorIds());
            existente.setTratadores(tratadores);
        } else {
            existente.setTratadores(new ArrayList<>());
        }

        recintoRepository.save(existente);
        return toDTO(existente);
    }

    
    // READ    
    @Transactional(readOnly = true)
    public List<RecintoDTO> listarTodos() {
        return recintoRepository.findAll()
                .stream()
                .map(RecintoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<RecintoDTO> listarTodos(Pageable pageable) {
        return recintoRepository.findAll(pageable)
                .map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public RecintoDTO buscarPorId(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));
        return toDTO(recinto);
    }

    @Transactional(readOnly = true)
    public List<RecintoDTO> buscarPorArea(Float minimo, Float maximo) {
        return recintoRepository.findByAreaHabitavelBetween(minimo, maximo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecintoDTO> buscarPorPopulacao(Integer minimo, Integer maximo) {
        return recintoRepository.findByPopulacaoBetween(minimo, maximo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    
    // MAPPER: ENTITY → DTO    
    private RecintoDTO toDTO(Recinto recinto) {
        RecintoDTO dto = new RecintoDTO();

        dto.setId(recinto.getId());
        dto.setNome(recinto.getNome());
        dto.setStatus(recinto.getStatus());
        dto.setTipo(recinto.getTipo());
        dto.setAreaHabitavel(recinto.getAreaHabitavel());
        dto.setPopulacao(recinto.getPopulacao());

        if (recinto.getPlanoDieta() != null) {
            dto.setPlanoDietaId(recinto.getPlanoDieta().getId());
        }

        if (recinto.getTratadores() != null) {
            dto.setTratadorIds(
                    recinto.getTratadores().stream()
                            .map(Tratador::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }


    // MAPPER: DTO → ENTITY    
    private Recinto toEntity(RecintoDTO dto) {
        Recinto recinto = new Recinto();

        recinto.setId(dto.getId());
        recinto.setNome(dto.getNome());
        recinto.setTipo(dto.getTipo());
        recinto.setStatus(dto.getStatus());
        recinto.setAreaHabitavel(dto.getAreaHabitavel());
        recinto.setPopulacao(dto.getPopulacao());

        if (dto.getPlanoDietaId() != null) {
            PlanoDieta plano = planoDietaRepository.findById(dto.getPlanoDietaId())
                    .orElseThrow(() -> new EntityNotFoundException("Plano de dieta não encontrado."));
            recinto.setPlanoDieta(plano);
        }

        if (dto.getTratadorIds() != null) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadorIds());
            recinto.setTratadores(tratadores);
        }

        return recinto;
    }

}
