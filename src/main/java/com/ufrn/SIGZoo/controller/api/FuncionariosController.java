package com.ufrn.SIGZoo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.SIGZoo.model.entity.Funcionario;
import com.ufrn.SIGZoo.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public ResponseEntity<List<Funcionario>> listarTodosFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/remuneracao")
    public ResponseEntity<List<Funcionario>> listarFuncionariosPorRemuneracao(
        @RequestParam(required = false) Float maiorQue,
        @RequestParam(required = false) Float menorQue
    ) {
        List<Funcionario> funcionarios = funcionarioService.listarPorRemuneracao(
            maiorQue != null ? maiorQue : 0,
            menorQue != null ? menorQue : Float.POSITIVE_INFINITY
        );
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/especializacao/{especializacao}")
    public ResponseEntity<List<Funcionario>> listarFuncionariosPorEspecializacao(@PathVariable String especializacao) {
        List<Funcionario> funcionarios = funcionarioService.listarPorEspecializacao(especializacao);
        return ResponseEntity.ok(funcionarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
