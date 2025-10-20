package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.exception.RNException;
import com.ufrn.SIGZoo.model.dto.VeterinarioDTO;
import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.repository.FuncionarioRepository;
import com.ufrn.SIGZoo.repository.VeterinarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VeterinarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Transactional
    public VeterinarioDTO criar(VeterinarioDTO veterinario) {

        if (funcionarioRepository.existsByCpf(veterinario.getCpf())) {
            throw new RNException("CPF já cadastrado no sistema.");
        }

        if (veterinarioRepository.existsByCrmv(veterinario.getCrmv())) {
            throw new RNException("CRMV já cadastrado no sistema.");
        }

        Veterinario novoVet = veterinario.toEntity();

        novoVet.setDataIngresso(java.time.LocalDate.now());

        veterinarioRepository.save(novoVet);

        return toDTO(novoVet);
    }

    @Transactional
    public VeterinarioDTO atualizar(Integer id, VeterinarioDTO vetAtualizadoDto) {
        Veterinario vetExistente = veterinarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));

        funcionarioRepository.findByCpf(vetAtualizadoDto.getCpf()).ifPresent(funcPresente -> {
            if (!funcPresente.getId().equals(vetExistente.getId())) {
                throw new RNException("CPF já cadastrado para outro funcionário.");
            }
        });

        veterinarioRepository.findByCrmv(vetAtualizadoDto.getCrmv()).ifPresent(vetPresente -> { 
            if (!vetPresente.getId().equals(vetExistente.getId())) {
                throw new RNException("CRMV já cadastrado para outro veterinário.");
            }
        });   

        vetExistente.setNome(vetAtualizadoDto.getNome());
        vetExistente.setCpf(vetAtualizadoDto.getCpf()); 
        vetExistente.setCrmv(vetAtualizadoDto.getCrmv()); 
        vetExistente.setEspecializacao(vetAtualizadoDto.getEspecializacao());
        vetExistente.setNascimento(vetAtualizadoDto.getNascimento());
        vetExistente.setRemuneracao(vetAtualizadoDto.getRemuneracao());
        vetExistente.setDataIngresso(vetAtualizadoDto.getDataIngresso());

        Veterinario vetSalvo = veterinarioRepository.save(vetExistente);

        return toDTO(vetSalvo);
    }

    @Transactional
    public void deletar(Integer id) {
        veterinarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<VeterinarioDTO> listarTodos() {
        return listDTO(veterinarioRepository.findAll());
    }

    @Transactional(readOnly = true)
    public VeterinarioDTO buscarPorId(Integer id) {
        Veterinario vet = veterinarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        return toDTO(vet);
    }

    @Transactional(readOnly = true)
    public List<VeterinarioDTO> listarPorQtdPacientes() {
        return listDTO(veterinarioRepository.findAllByOrderByQtdPacientesDesc());
    }

    private VeterinarioDTO toDTO(Veterinario vet) {
        if (vet == null) {
            return null;
        }
        VeterinarioDTO dto = new VeterinarioDTO();

        dto.setId(vet.getId());
        dto.setCpf(vet.getCpf());
        dto.setCrmv(vet.getCrmv());
        dto.setDataIngresso(vet.getDataIngresso());
        dto.setEspecializacao(vet.getEspecializacao());
        dto.setNascimento(vet.getNascimento());
        dto.setNome(vet.getNome());
        dto.setRemuneracao(vet.getRemuneracao());
        dto.setQtdPacientes(vet.getQtdPacientes());

        return dto;
    }    

    private List<VeterinarioDTO> listDTO(List<Veterinario> vets) {
        return vets.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
