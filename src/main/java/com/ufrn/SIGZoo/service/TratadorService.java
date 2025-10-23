package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.exception.RNException;
import com.ufrn.SIGZoo.model.dto.TratadorDTO;
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
    public TratadorDTO criar(TratadorDTO tratador) {

        if (funcionarioRepository.existsByCpf(tratador.getCpf())) {
            throw new RNException("CPF já cadastrado no sistema.");
        }

        tratador.setDataIngresso(java.time.LocalDate.now());

        Tratador novoTrat = tratador.toEntity();

        tratadorRepository.save(novoTrat);

        return toDTO(novoTrat);
    }

    @Transactional
    public TratadorDTO atualizar(Integer id, TratadorDTO tratadorAtualizadoDto) {
        Tratador tratadorExistente = tratadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tratador não encontrado."));

        funcionarioRepository.findByCpf(tratadorAtualizadoDto.getCpf()).ifPresent(funcPresente -> {
            if (!funcPresente.getId().equals(tratadorExistente.getId())) {
                throw new RNException("CPF já cadastrado para outro funcionário.");
            }
        });
        
        tratadorExistente.setNome(tratadorAtualizadoDto.getNome());
        tratadorExistente.setCpf(tratadorAtualizadoDto.getCpf()); 
        tratadorExistente.setEspecializacao(tratadorAtualizadoDto.getEspecializacao());
        tratadorExistente.setNascimento(tratadorAtualizadoDto.getNascimento());
        tratadorExistente.setRemuneracao(tratadorAtualizadoDto.getRemuneracao());
        tratadorExistente.setDataIngresso(tratadorAtualizadoDto.getDataIngresso());  
        tratadorExistente.setTurno(tratadorAtualizadoDto.getTurno());    
        
        Tratador tratadorSalvo = tratadorRepository.save(tratadorExistente);

        return toDTO(tratadorSalvo);
    }

    @Transactional
    public void deletar(Integer id) {
        tratadorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TratadorDTO> listarTodos() {
        return listDTO(tratadorRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TratadorDTO buscarPorId(Integer id) {
        Tratador trat = tratadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tratador não encontrado."));
        return toDTO(trat);
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
    public List<TratadorDTO> buscarTratadoresOciosos() {
        return listDTO(tratadorRepository.findAllByQtdRecintosAtribuidos(0));
    }

    @Transactional(readOnly = true)
    public List<TratadorDTO> listarPorTurno(String turno) {
        return listDTO(tratadorRepository.findAllByTurno(turno));
    }

    @Transactional(readOnly = true)
    public List<TratadorDTO> listarPorQtdRecintos() {
        return listDTO(tratadorRepository.findAllByOrderByQtdRecintosAtribuidosDesc());
    }

    private TratadorDTO toDTO(Tratador trat) {
        if (trat == null) {
            return null;
        }
        TratadorDTO dto = new TratadorDTO();

        dto.setId(trat.getId());
        dto.setCpf(trat.getCpf());
        dto.setDataIngresso(trat.getDataIngresso());
        dto.setEspecializacao(trat.getEspecializacao());
        dto.setNascimento(trat.getNascimento());
        dto.setNome(trat.getNome());
        dto.setRemuneracao(trat.getRemuneracao());
        dto.setTurno(trat.getTurno());
        dto.setQtdRecintosAtribuidos(trat.getQtdRecintosAtribuidos());

        return dto;        
    }

    private List<TratadorDTO> listDTO(List<Tratador> trats) {
        return trats.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
