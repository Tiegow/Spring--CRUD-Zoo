package com.ufrn.SIGZoo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.SIGZoo.model.entity.Tratador;
import com.ufrn.SIGZoo.service.TratadorService;

@RestController
@RequestMapping("/api/tratadores")
public class TratadorController {

    @Autowired
    private TratadorService tratadorService;

    @GetMapping("")
    public ResponseEntity<List<Tratador>> listarTodosTratadores() {
        List<Tratador> tratadores = tratadorService.listarTodos();
        return ResponseEntity.ok(tratadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratador> buscarTratadorPorId(@PathVariable Integer id) {
        Tratador tratador = tratadorService.buscarPorId(id).orElse(null);
        if (tratador != null) {
            return ResponseEntity.ok(tratador);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listarPorTurno/{turno}")
    public ResponseEntity<List<Tratador>> listarTratadoresPorTurno(@PathVariable String turno) {
        List<Tratador> tratadores = tratadorService.listarPorTurno(turno);
        return ResponseEntity.ok(tratadores);
    }

    @GetMapping("/listarPorQtdRecintos")
    public ResponseEntity<List<Tratador>> listarTratadoresPorQtdRecintos() {
        List<Tratador> tratadores = tratadorService.listarPorQtdRecintos();
        return ResponseEntity.ok(tratadores);
    }

    @GetMapping("/listarOciosos")
    public ResponseEntity<List<Tratador>> listarTratadoresOciosos() {
        List<Tratador> tratadores = tratadorService.buscarTratadoresOciosos();
        return ResponseEntity.ok(tratadores);
    }

    @PostMapping("/criar")
    public ResponseEntity<Tratador> criarTratador(@RequestBody Tratador tratador) {
        Tratador novoTratador = tratadorService.criar(tratador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTratador);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Tratador> atualizarTratador(@PathVariable Integer id, @RequestBody Tratador tratador) {
        Tratador tratadorAtualizado = tratadorService.atualizar(id, tratador);
        if (tratadorAtualizado != null) {
            return ResponseEntity.ok(tratadorAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/atribuirRecinto/{tratadorId}/{recintoId}")
    public ResponseEntity<Void> atribuirRecinto(@PathVariable Integer tratadorId, @PathVariable Integer recintoId) {
        tratadorService.atribuirRecinto(tratadorId, recintoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/desatribuirRecinto/{tratadorId}/{recintoId}")
    public ResponseEntity<Void> desatribuirRecinto(@PathVariable Integer tratadorId, @PathVariable Integer recintoId) {
        tratadorService.desatribuirRecinto(tratadorId, recintoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTratador(@PathVariable Integer id) {
        tratadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
