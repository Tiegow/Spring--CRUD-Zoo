package com.ufrn.SIGZoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufrn.SIGZoo.model.dto.EventoDTO;
import com.ufrn.SIGZoo.model.entity.Evento;
import com.ufrn.SIGZoo.repository.EventoRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private RecintoRepository recintoRepository;

    
    // CREATE    
    @Transactional
    public EventoDTO criar(EventoDTO dto) {
        Evento evento = toEntity(dto);

        eventoRepository.save(evento);
        return toDTO(evento);
    }

    
    // DELETE    
    @Transactional
    public void deletar(Integer id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado."));
        eventoRepository.delete(evento);
    }

    
    // UPDATE    
    @Transactional
    public EventoDTO atualizar(Integer id, EventoDTO dto) {
        Evento existente = eventoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado."));

        existente.setNome(dto.getNome());
        existente.setData(dto.getData());
        existente.setCapacidade(dto.getCapacidade());
        existente.setTipo(dto.getTipo());

        if (dto.getRecintosIds() != null) {
            var recintos = recintoRepository.findAllById(dto.getRecintosIds());
            existente.setRecintos(recintos);
        }

        eventoRepository.save(existente);
        return toDTO(existente);
    }

    
    // READ    
    @Transactional(readOnly = true)
    public Page<EventoDTO> listarTodos(Pageable pageable) {
        return eventoRepository.findAll(pageable).map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<EventoDTO> listarPorCapacidade(Pageable pageable, Integer valMin, Integer valMax) {

        boolean minVazio = (valMin == null || valMin == 0);
        boolean maxVazio = (valMax == null || valMax == 0);

        if (minVazio && maxVazio) {
            return listarTodos(pageable);
        }

        Page<Evento> page;

        if (!minVazio && !maxVazio) {
            page = eventoRepository.findByCapacidadeBetween(valMin, valMax, pageable);
        } else if (!minVazio) {
            page = eventoRepository.findByCapacidadeGreaterThanEqual(valMin, pageable);
        } else {
            page = eventoRepository.findByCapacidadeLessThanEqual(valMax, pageable);
        }

        return page.map(this::toDTO);
    }


    @Transactional(readOnly = true)
    public EventoDTO buscarPorId(Integer id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado."));
        return toDTO(evento);
    }

    
    // MAPPER: ENTITY → DTO    
    private EventoDTO toDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();

        dto.setId(evento.getId());
        dto.setNome(evento.getNome());
        dto.setData(evento.getData());
        dto.setCapacidade(evento.getCapacidade());
        dto.setTipo(evento.getTipo());

        if (evento.getRecintos() != null)
            dto.setRecintosIds(
                evento.getRecintos()
                    .stream()
                    .map(r -> r.getId())
                    .toList()
            );

        return dto;
    }

    
    // MAPPER: DTO → ENTITY    
    private Evento toEntity(EventoDTO dto) {
        Evento evento = new Evento();

        evento.setId(dto.getId());
        evento.setNome(dto.getNome());
        evento.setData(dto.getData());
        evento.setCapacidade(dto.getCapacidade());
        evento.setTipo(dto.getTipo());

        if (dto.getRecintosIds() != null) {
            var recintos = recintoRepository.findAllById(dto.getRecintosIds());
            evento.setRecintos(recintos);
        }

        return evento;
    }

    @Transactional(readOnly = true)
    public Long obterQtdEventos() {
        return eventoRepository.count();
    }
}
