package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.exception.RNException;
import com.ufrn.SIGZoo.model.entity.Recinto;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.repository.FuncionarioRepository;
import com.ufrn.SIGZoo.repository.RecintoRepository;
import com.ufrn.SIGZoo.repository.TratadorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TratadorService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private TratadorRepository tratadorRepository;

    @Autowired
    private RecintoRepository recintoRepository;

    @Transactional
    public Tratador criar(Tratador tratador) {

        if (funcionarioRepository.existsByCpf(tratador.getCpf())) {
            throw new RNException("CPF já cadastrado no sistema.");
        }

        tratador.setDataIngresso(java.time.LocalDate.now());

        return tratadorRepository.save(tratador);
    }

    @Transactional
    public Tratador atualizar(Integer id, Tratador tratadorAtualizado) {
        Tratador tratadorExistente = tratadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tratador não encontrado."));

        funcionarioRepository.findByCpf(tratadorAtualizado.getCpf()).ifPresent(funcPresente -> {
            if (!funcPresente.getId().equals(tratadorExistente.getId())) {
                throw new RNException("CPF já cadastrado para outro funcionário.");
            }
        });
        
        tratadorAtualizado.setId(tratadorExistente.getId());

        return tratadorRepository.save(tratadorAtualizado);
    }

    @Transactional
    public void deletar(Integer id) {
        tratadorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Tratador> listarTodos() {
        return tratadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Tratador> buscarPorId(Integer id) {
        return tratadorRepository.findById(id);
    }

    @Transactional
    public Tratador atribuirRecinto(Integer tratadorId, Integer recintoId) {
        Tratador tratador = tratadorRepository.findById(tratadorId).orElseThrow(() -> new EntityNotFoundException("Tratador não encontrado."));
        Recinto recinto = recintoRepository.findById(recintoId).orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));

        // Tratador já cuida do recinto
        if (tratador.getRecintosAtribuidos().contains(recinto)) {
            return tratador;
        }

        tratador.incrementarRecintos();
        tratador.getRecintosAtribuidos().add(recinto);

        return tratadorRepository.save(tratador);
    }

    @Transactional
    public Tratador desatribuirRecinto(Integer tratadorId, Integer recintoId) {
        Tratador tratador = tratadorRepository.findById(tratadorId).orElseThrow(() -> new EntityNotFoundException("Tratador não encontrado."));
        Recinto recinto = recintoRepository.findById(recintoId).orElseThrow(() -> new EntityNotFoundException("Recinto não encontrado."));

        if (tratador.getRecintosAtribuidos().contains(recinto)) {
            tratador.decrementarRecintos();
            tratador.getRecintosAtribuidos().remove(recinto);

            return tratadorRepository.save(tratador);
        }
        
        return tratador;
    }

    @Transactional(readOnly = true)
    public List<Tratador> buscarTratadoresOciosos() {
        return tratadorRepository.findAllByQtdRecintosAtribuidos(0);
    }

    @Transactional(readOnly = true)
    public List<Tratador> listarPorTurno(String turno) {
        return tratadorRepository.findAllByTurno(turno);
    }

    @Transactional(readOnly = true)
    public List<Tratador> listarPorQtdRecintos() {
        return tratadorRepository.findAllByOrderByQtdRecintosAtribuidosDesc();
    }
}
