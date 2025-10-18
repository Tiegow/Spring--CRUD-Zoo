package com.ufrn.SIGZoo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.service.VeterinarioService;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("")
    public ResponseEntity<List<Veterinario>> listarTodosVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.listarTodos();
        return ResponseEntity.ok(veterinarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> buscarVeterinarioPorId(@PathVariable Integer id) {
        Veterinario veterinario = veterinarioService.buscarPorId(id).orElse(null);

        return veterinario == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(veterinario);
    }

    @GetMapping("/listarPorQtdPacientes")
    public ResponseEntity<List<Veterinario>> listarVeterinariosPorQtdPacientes() {
        List<Veterinario> veterinarios = veterinarioService.listarPorQtdPacientes();
        return ResponseEntity.ok(veterinarios);
    }

    @PostMapping("/criar")
    public ResponseEntity<Veterinario> cadastrarVeterinario(@RequestBody Veterinario veterinario) {
        Veterinario novoVeterinario = veterinarioService.criar(veterinario);
        return ResponseEntity.ok(novoVeterinario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Veterinario> atualizarVeterinario(@PathVariable Integer id, @RequestBody Veterinario veterinario) {
        Veterinario vetAtualizado = veterinarioService.atualizar(id, veterinario);
        if (vetAtualizado != null) {
            return ResponseEntity.ok(vetAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarVeterinario(@PathVariable Integer id) {
        veterinarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
