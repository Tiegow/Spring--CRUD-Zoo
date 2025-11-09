package com.ufrn.SIGZoo.service;

<<<<<<< Updated upstream
=======
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

>>>>>>> Stashed changes
import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.model.entity.PlanoDieta;
import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.repository.PlanoDietaRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;
<<<<<<< Updated upstream
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
=======
import com.ufrn.SIGZoo.repository.TratadorRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

>>>>>>> Stashed changes
@Service
public class RecintoService {

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private PlanoDietaRepository planoDietaRepository;

    @Autowired
<<<<<<< Updated upstream
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
=======
    private TratadorRepository tratadorRepository;

    
    // CREATE    
    @Transactional
    public RecintoDTO criar(RecintoDTO dto) {
        Recinto recinto = toEntity(dto);

        recintoRepository.save(recinto);
        return toDTO(recinto);
    }

    
    // DELETE
    @Transactional
    public void deletar(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));
        recintoRepository.delete(recinto);
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

        if (dto.getPlanoDietaId() != null) {
            PlanoDieta plano = planoDietaRepository.findById(dto.getPlanoDietaId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de dieta não encontrado."));
            existente.setPlanoDieta(plano);
        }

        if (dto.getTratadoresIds() != null) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadoresIds());
            existente.setTratadores(tratadores);
        }

        recintoRepository.save(existente);
        return toDTO(existente);
    }

    
    // READ
    @Transactional(readOnly = true)
    public Page<RecintoDTO> listarTodos(Pageable pageable) {
        Page<Recinto> page = recintoRepository.findAll(pageable);
        return page.map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public RecintoDTO buscarPorId(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));
        return toDTO(recinto);
    }

    // MAPPER: ENTITY → DTO
    private RecintoDTO toDTO(Recinto recinto) {
        RecintoDTO dto = new RecintoDTO();

        dto.setId(recinto.getId());
        dto.setNome(recinto.getNome());
        dto.setStatus(recinto.getStatus());
        dto.setTipo(recinto.getTipo());
        dto.setAreaHabitavel(recinto.getAreaHabitavel());

        if (recinto.getPlanoDieta() != null)
            dto.setPlanoDietaId(recinto.getPlanoDieta().getId());

        if (recinto.getTratadores() != null)
            dto.setTratadoresIds(recinto.getTratadores().stream()
                .map(t -> t.getId())
                .collect(Collectors.toList()));

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

        if (dto.getPlanoDietaId() != null) {
            PlanoDieta plano = planoDietaRepository.findById(dto.getPlanoDietaId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de dieta não encontrado."));
            recinto.setPlanoDieta(plano);
        }

        if (dto.getTratadoresIds() != null) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadoresIds());
            recinto.setTratadores(tratadores);
        }

        return recinto;
    }
}
>>>>>>> Stashed changes
