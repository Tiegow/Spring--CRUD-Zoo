package com.ufrn.SIGZoo.service;

import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.model.entity.PlanoDieta;
import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.repository.PlanoDietaRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;
import com.ufrn.SIGZoo.repository.TratadorRepository; 
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList; 
@Service
public class RecintoService {

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private PlanoDietaRepository planoDietaRepository;

    @Autowired
    private TratadorRepository tratadorRepository; 

    @Transactional(readOnly = true)
    public RecintoDTO buscarPorId(Integer id) {
        Recinto recinto = recintoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recinto com ID " + id + " não encontrado!"));
        
        return RecintoDTO.fromEntity(recinto);
    }

    @Transactional(readOnly = true)
    public List<RecintoDTO> listarTodos() {
        List<Recinto> recintos = recintoRepository.findAll();
        
        return recintos.stream()
                .map(RecintoDTO::fromEntity)
                .collect(Collectors.toList());
    }    

    @Transactional(readOnly = true)
    public Page<RecintoDTO> listarTodos(Pageable pageable) {
        Page<Recinto> paginaDeRecintos = recintoRepository.findAll(pageable);
        
        return paginaDeRecintos.map(RecintoDTO::fromEntity);
    }    

    @Transactional
    public RecintoDTO criar(RecintoDTO dto) {
        Recinto recinto = dto.toEntity();

        setRelationshipsFromDto(recinto, dto);

        Recinto recintoSalvo = recintoRepository.save(recinto);

        return RecintoDTO.fromEntity(recintoSalvo);
    }

    @Transactional
    public void deletar(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto com ID " + id + " não encontrado!"));

        if (recinto.getAnimais() != null && !recinto.getAnimais().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível deletar um recinto que contém animais.");
        }

        recintoRepository.delete(recinto);
    }

    @Transactional
    public RecintoDTO atualizar(Integer id, RecintoDTO dto) {
        Recinto recintoExistente = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto com ID " + id + " não encontrado!"));

        recintoExistente.setNome(dto.getNome());
        recintoExistente.setAreaHabitavel(dto.getAreaHabitavel());
        recintoExistente.setStatus(dto.getStatus());

        setRelationshipsFromDto(recintoExistente, dto);

        Recinto recintoAtualizado = recintoRepository.save(recintoExistente);

        return RecintoDTO.fromEntity(recintoAtualizado);
    }

    private void setRelationshipsFromDto(Recinto recinto, RecintoDTO dto) {
        if (dto.getPlanoDietaId() != null) {
            PlanoDieta planoDieta = planoDietaRepository.findById(dto.getPlanoDietaId())
                    .orElseThrow(() -> new EntityNotFoundException("Plano de dieta com ID " + dto.getPlanoDietaId() + " não encontrado!"));
            recinto.setPlanoDieta(planoDieta);
        } else {
            recinto.setPlanoDieta(null); 
        }

        if (dto.getTratadorIds() != null && !dto.getTratadorIds().isEmpty()) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadorIds());
            recinto.setTratadores(tratadores);
        } else {
            recinto.setTratadores(new ArrayList<>()); 
        }
    }

    public long obterQtdRecintos() {
        return recintoRepository.count();
    }
}