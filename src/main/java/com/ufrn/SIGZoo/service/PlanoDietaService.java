package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.dto.PlanoDietaDTO;
import com.ufrn.SIGZoo.model.entity.PlanoDieta;
import com.ufrn.SIGZoo.repository.PlanoDietaRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlanoDietaService {

    @Autowired
    private PlanoDietaRepository planoDietaRepository;

    @Autowired
    private RecintoRepository recintoRepository; 

    @Transactional
    public PlanoDietaDTO criar(PlanoDietaDTO dto) {
        PlanoDieta plano = dto.toEntity();
        PlanoDieta planoSalvo = planoDietaRepository.save(plano);
        return PlanoDietaDTO.fromEntity(planoSalvo);
    }

    @Transactional
    public PlanoDietaDTO atualizar(Integer id, PlanoDietaDTO dto) {
        PlanoDieta planoExistente = planoDietaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plano de dieta com ID " + id + " não encontrado."));

        planoExistente.setQuantidadeCarne(dto.getQuantidadeCarne());
        planoExistente.setQuantidadeVegetais(dto.getQuantidadeVegetais());

        PlanoDieta planoAtualizado = planoDietaRepository.save(planoExistente);
        return PlanoDietaDTO.fromEntity(planoAtualizado);
    }

    @Transactional
    public void deletar(Integer id) {
        PlanoDieta plano = planoDietaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plano de dieta com ID " + id + " não encontrado."));

        if (recintoRepository.existsByPlanoDietaId(id)) {
            throw new DataIntegrityViolationException("Não é possível deletar este plano de dieta, pois ele está em uso por um ou mais recintos.");
        }

        planoDietaRepository.delete(plano);
    }

    @Transactional(readOnly = true)
    public PlanoDietaDTO buscarPorId(Integer id) {
        PlanoDieta plano = planoDietaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano de dieta com ID " + id + " não encontrado."));
        return PlanoDietaDTO.fromEntity(plano);
    }

    @Transactional(readOnly = true)
    public List<PlanoDietaDTO> listarTodos() {
        List<PlanoDieta> planos = planoDietaRepository.findAll();
        return planos.stream()
                .map(PlanoDietaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PlanoDietaDTO> listarTodos(Pageable pageable) {
        Page<PlanoDieta> paginaDePlanos = planoDietaRepository.findAll(pageable);
        return paginaDePlanos.map(PlanoDietaDTO::fromEntity);
    }
}
