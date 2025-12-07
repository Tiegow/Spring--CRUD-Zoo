package com.ufrn.SIGZoo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.dto.IngressoDTO;
import com.ufrn.SIGZoo.model.entity.Ingresso;
import com.ufrn.SIGZoo.repository.IngressoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Transactional(readOnly = true)
    public List<IngressoDTO> listarTodos() {
        return ingressoRepository.findAll()
                .stream()
                .map(IngressoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<IngressoDTO> listarTodos(Pageable pageable) {
        return ingressoRepository.findAll(pageable)
                .map(IngressoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public IngressoDTO buscarPorId(Integer id) {
        Ingresso ingresso = ingressoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ingresso com ID " + id + " não encontrado."));

        return IngressoDTO.fromEntity(ingresso);
    }

    @Transactional
    public IngressoDTO criar(IngressoDTO dto) {
        Ingresso ingresso = dto.toEntity();
        ingressoRepository.save(ingresso);
        return IngressoDTO.fromEntity(ingresso);
    }

    @Transactional
    public IngressoDTO atualizar(Integer id, IngressoDTO dto) {

        Ingresso ingresso = ingressoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ingresso com ID " + id + " não encontrado."));

        ingresso.setDataCompra(dto.getDataCompra());
        ingresso.setHoraCompra(dto.getHoraCompra());
        ingresso.setDataVisita(dto.getDataVisita());
        ingresso.setHoraVisita(dto.getHoraVisita());
        ingresso.setCusto(dto.getCusto());

        ingressoRepository.save(ingresso);

        return IngressoDTO.fromEntity(ingresso);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!ingressoRepository.existsById(id)) {
            throw new EntityNotFoundException("Ingresso com ID " + id + " não encontrado.");
        }
        ingressoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<IngressoDTO> filtrarPorDataCompra(LocalDate inicio, LocalDate fim) {
        return ingressoRepository
                .findByDataCompraBetween(inicio, fim)
                .stream()
                .map(IngressoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<IngressoDTO> filtrarPorDataVisita(LocalDate inicio, LocalDate fim) {
        return ingressoRepository
                .findByDataVisitaBetween(inicio, fim)
                .stream()
                .map(IngressoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<IngressoDTO> filtrarPorCusto(double min, double max) {
        return ingressoRepository
                .findByCustoBetween(min, max)
                .stream()
                .map(IngressoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<IngressoDTO> filtrarPorDataCompra(
            LocalDate inicio, LocalDate fim, Pageable pageable) {

        return ingressoRepository
                .findByDataCompraBetween(inicio, fim, pageable)
                .map(IngressoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<IngressoDTO> filtrarPorDataVisita(
            LocalDate inicio, LocalDate fim, Pageable pageable) {

        return ingressoRepository
                .findByDataVisitaBetween(inicio, fim, pageable)
                .map(IngressoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<IngressoDTO> filtrarPorCusto(
            double min, double max, Pageable pageable) {

        return ingressoRepository
                .findByCustoBetween(min, max, pageable)
                .map(IngressoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Long obterQtdIngressos() {
        return ingressoRepository.count();
    }
}
