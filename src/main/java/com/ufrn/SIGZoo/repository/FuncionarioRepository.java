package com.ufrn.SIGZoo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    boolean existsByCpf(String cpf);
    Optional<Funcionario> findByCpf(String cpf);
    
    List<Funcionario> findAllByOrderByNome();

    List<Funcionario> findByEspecializacao(String especializacao);
    List<Funcionario> findByRemuneracaoGreaterThan(float remuneracao);
    List<Funcionario> findByRemuneracaoLessThan(float remuneracao);
}
