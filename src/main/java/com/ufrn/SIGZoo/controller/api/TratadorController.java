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

import com.ufrn.SIGZoo.model.dto.TratadorDTO;
import com.ufrn.SIGZoo.service.TratadorService;

@RestController
@RequestMapping("/api/tratadores")
public class TratadorController {

    @Autowired
    private TratadorService tratadorService;

    @GetMapping("")
    public ResponseEntity<List<TratadorDTO>> listarTodosTratadores() {
        List<TratadorDTO> tratadores = tratadorService.listarTodos();

        return ResponseEntity.ok(tratadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratadorDTO> buscarTratadorPorId(@PathVariable Integer id) {
        TratadorDTO tratador = tratadorService.buscarPorId(id);

        return ResponseEntity.ok(tratador);
    }

    @GetMapping("/listarPorTurno/{turno}")
    public ResponseEntity<List<TratadorDTO>> listarTratadoresPorTurno(@PathVariable String turno) {
        List<TratadorDTO> tratadores = tratadorService.listarPorTurno(turno);

        return ResponseEntity.ok(tratadores);
    }

    @GetMapping("/listarPorQtdRecintos")
    public ResponseEntity<List<TratadorDTO>> listarTratadoresPorQtdRecintos() {
        List<TratadorDTO> tratadores = tratadorService.listarPorQtdRecintos();

        return ResponseEntity.ok(tratadores);
    }

    @GetMapping("/listarOciosos")
    public ResponseEntity<List<TratadorDTO>> listarTratadoresOciosos() {
        List<TratadorDTO> tratadores = tratadorService.buscarTratadoresOciosos();

        return ResponseEntity.ok(tratadores);
    }

    @PostMapping("/criar")
    public ResponseEntity<TratadorDTO> criarTratador(@RequestBody TratadorDTO tratador) {
        TratadorDTO novoTratador = tratadorService.criar(tratador);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoTratador);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TratadorDTO> atualizarTratador(@PathVariable Integer id, @RequestBody TratadorDTO tratador) {
        TratadorDTO tratadorAtualizado = tratadorService.atualizar(id, tratador);
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
