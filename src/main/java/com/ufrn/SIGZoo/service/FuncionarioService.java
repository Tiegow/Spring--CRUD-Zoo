package com.ufrn.SIGZoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.entity.Funcionario;
import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.repository.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    TratadorService tratadorService;

    @Transactional(readOnly = true)
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAllByOrderByNome();
    }

    @Transactional(readOnly = true)
    public List<Funcionario> listarPorEspecializacao(String especializacao) {
        if (especializacao.equals("") || especializacao.isEmpty() || especializacao.isBlank() || especializacao.equals(null)) {
            return listarTodos();
        }
        return funcionarioRepository.findByEspecializacao(especializacao);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> listarPorRemuneracao(float maiorQue, float menorQue) {
        List<Funcionario> listaMaior = funcionarioRepository.findByRemuneracaoGreaterThan(maiorQue);
        List<Funcionario> listaMenor = funcionarioRepository.findByRemuneracaoLessThan(menorQue);
        listaMaior.retainAll(listaMenor); // "Intersecta" as duas listas
        return listaMaior;
    }

    @Transactional(readOnly = true)
    public long obterQtdFuncionarios() {
        return funcionarioRepository.count();
    }

    // Método "despachante" para deletar um funcionário
    @Transactional
    public void deletarFuncionario(Integer id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário com ID " + id + " não encontrado."));

        if (funcionario instanceof Veterinario) {
            veterinarioService.deletar(id);

        } else if (funcionario instanceof Tratador) {
            tratadorService.deletar(id);

        } else {
            throw new IllegalStateException("Tipo de funcionário desconhecido para exclusão: " + funcionario.getClass().getName());
        }
    }
}