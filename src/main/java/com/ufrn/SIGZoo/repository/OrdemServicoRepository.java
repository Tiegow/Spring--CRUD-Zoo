package com.ufrn.SIGZoo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.SIGZoo.model.entity.Funcionario;
import com.ufrn.SIGZoo.model.entity.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    List<OrdemServico> findByStatus(String status);

    List<OrdemServico> findByFuncionariosContaining(Funcionario funcionario);

    List<OrdemServico> findAllByFuncionariosId(Integer funcionarioId);

    List<OrdemServico> findByDataInicioGreaterThanEqualAndDataConclusaoLessThanEqual(
        Date dataInicio,
        Date dataConclusao
    );
}
