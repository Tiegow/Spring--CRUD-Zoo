package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufrn.SIGZoo.model.dto.PlanoDietaDTO;
import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.model.entity.Animal;
import com.ufrn.SIGZoo.model.entity.PlanoDieta;
import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.repository.AnimalRepository;
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

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public void deletar(Integer id) {
        Recinto recinto = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto com ID " + id + " não encontrado!"));

        if (recinto.getAnimais() != null && !recinto.getAnimais().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível deletar um recinto que contém animais.");
        }
        
        PlanoDieta plano = recinto.getPlanoDieta();
        if (plano != null) {
            recinto.setPlanoDieta(null);
            planoDietaRepository.delete(plano);
        }

        recintoRepository.delete(recinto);
    }

    public long obterQtdRecintos() {
        return recintoRepository.count();
    }

    @Transactional
    public RecintoDTO criar(RecintoDTO dto) {
        Recinto recinto = toEntity(dto); 

        if (dto.getPlanoDieta() != null) {
            PlanoDieta novoPlano = dto.getPlanoDieta().toEntity();
            PlanoDieta planoSalvo = planoDietaRepository.save(novoPlano);
            recinto.setPlanoDieta(planoSalvo);
        }
        
        recinto = recintoRepository.save(recinto);

        if (dto.getAnimaisIds() != null && !dto.getAnimaisIds().isEmpty()) {
            List<Animal> animais = animalRepository.findAllById(dto.getAnimaisIds());
            for (Animal a : animais) {
                a.setRecinto(recinto);
            }
            recinto.setAnimais(animais);
        }
        
        recinto = recintoRepository.save(recinto);

        return toDTO(recinto);
    }

    @Transactional
    public RecintoDTO atualizar(Integer id, RecintoDTO dto) {
        Recinto existente = recintoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));

        existente.setNome(dto.getNome());
        existente.setStatus(dto.getStatus());
        existente.setAreaHabitavel(dto.getAreaHabitavel());

        if (dto.getPlanoDieta() != null) {
            PlanoDieta planoParaAtualizar;
            if (existente.getPlanoDieta() != null) {
                planoParaAtualizar = existente.getPlanoDieta();
            } else {
                planoParaAtualizar = new PlanoDieta();
            }
            
            planoParaAtualizar.setQuantidadeCarne(dto.getPlanoDieta().getQuantidadeCarne());
            planoParaAtualizar.setQuantidadeVegetais(dto.getPlanoDieta().getQuantidadeVegetais());
            
            PlanoDieta planoSalvo = planoDietaRepository.save(planoParaAtualizar);
            existente.setPlanoDieta(planoSalvo);
        } else {
            existente.setPlanoDieta(null);
        }

        if (dto.getAnimaisIds() != null) {
            List<Animal> novosAnimais = animalRepository.findAllById(dto.getAnimaisIds());
            
            if (existente.getAnimais() != null) {
                for (Animal a : existente.getAnimais()) {
                    if (!dto.getAnimaisIds().contains(a.getId())) {
                        a.setRecinto(null);
                    }
                }
            }
            for (Animal a : novosAnimais) {
                a.setRecinto(existente);
            }
            existente.setAnimais(novosAnimais);
        }

        if (dto.getTratadorIds() != null) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadorIds());
            existente.setTratadores(tratadores);
        } else {
            existente.setTratadores(new ArrayList<>());
        }

        recintoRepository.save(existente);
        return toDTO(existente);
    }

    @Transactional(readOnly = true)
    public List<RecintoDTO> listarTodos() {
        return recintoRepository.findAll()
                .stream()
                .map(this::toDTO)
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
        List<Recinto> recintos;

        if (minimo != null && maximo != null) {
            recintos = recintoRepository.findByAreaHabitavelBetween(minimo, maximo);
        } else if (minimo != null) {
            recintos = recintoRepository.findByAreaHabitavelGreaterThanEqual(minimo);
        } else if (maximo != null) {
            recintos = recintoRepository.findByAreaHabitavelLessThanEqual(maximo);
        } else {
            recintos = new ArrayList<>();
        }

        return recintos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecintoDTO> buscarPorPopulacao(Integer minimo, Integer maximo) {
        List<Recinto> recintos;

        if (minimo != null && maximo != null) {
        recintos = recintoRepository.findByPopulacaoBetween(minimo, maximo);
        } else if (minimo != null) {
        recintos = recintoRepository.findByPopulacaoGreaterThanEqual(minimo);
        } else if (maximo != null) {
        recintos = recintoRepository.findByPopulacaoLessThanEqual(maximo);
        } else {
        recintos = new ArrayList<>();
        }

        return recintos.stream()
            .map(this::toDTO) 
            .collect(Collectors.toList());
    }

    private RecintoDTO toDTO(Recinto recinto) {
        RecintoDTO dto = new RecintoDTO();

        dto.setId(recinto.getId());
        dto.setNome(recinto.getNome());
        dto.setStatus(recinto.getStatus());
        dto.setAreaHabitavel(recinto.getAreaHabitavel());
        dto.setPopulacao(recinto.getAnimais() != null ? recinto.getAnimais().size() : 0);

        if (recinto.getPlanoDieta() != null) {
            dto.setPlanoDieta(PlanoDietaDTO.fromEntity(recinto.getPlanoDieta()));
        }

        if (recinto.getAnimais() != null) {
            dto.setAnimaisIds(
                recinto.getAnimais()
                        .stream()
                        .map(Animal::getId)
                        .collect(Collectors.toList())
            );
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

    private Recinto toEntity(RecintoDTO dto) {
        Recinto recinto = new Recinto();

        recinto.setId(dto.getId());
        recinto.setNome(dto.getNome());
        recinto.setStatus(dto.getStatus());
        recinto.setAreaHabitavel(dto.getAreaHabitavel()); 
        
        if (dto.getTratadorIds() != null) {
            List<Tratador> tratadores = tratadorRepository.findAllById(dto.getTratadorIds());
            recinto.setTratadores(tratadores);
        }

        return recinto;
    }
}
