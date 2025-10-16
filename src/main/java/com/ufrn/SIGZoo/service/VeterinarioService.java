package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.exception.RNException;
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
    public Veterinario criar(Veterinario veterinario) {

        if (funcionarioRepository.existsByCpf(veterinario.getCpf())) {
            throw new RNException("CPF já cadastrado no sistema.");
        }

        if (veterinarioRepository.existsByCrmv(veterinario.getCrmv())) {
            throw new RNException("CRMV já cadastrado no sistema.");
        }

        veterinario.setDataIngresso(java.time.LocalDate.now());

        return veterinarioRepository.save(veterinario);
    }

    @Transactional
    public Veterinario atualizar(Integer id, Veterinario vetAtualizado) {
        Veterinario vetExistente = veterinarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));

        funcionarioRepository.findByCpf(vetAtualizado.getCpf()).ifPresent(funcPresente -> {
            if (!funcPresente.getId().equals(vetExistente.getId())) {
                throw new RNException("CPF já cadastrado para outro funcionário.");
            }
        });

        veterinarioRepository.findByCrmv(vetAtualizado.getCrmv()).ifPresent(funcPresente -> {
            if (!funcPresente.getId().equals(vetExistente.getId())) {
                throw new RNException("CRMV já cadastrado para outro veterinário.");
            }
        });   

        vetAtualizado.setId(vetExistente.getId());

        return veterinarioRepository.save(vetAtualizado);
    }

    @Transactional
    public void deletar(Integer id) {
        veterinarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Veterinario> buscarPorId(Integer id) {
        return veterinarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Veterinario> listarPorQtdPacientes() {
        return veterinarioRepository.findAllByOrderByQtdPacientesDesc();
    }
}
