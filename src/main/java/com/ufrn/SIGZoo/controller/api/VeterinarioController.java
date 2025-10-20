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

import com.ufrn.SIGZoo.model.dto.VeterinarioDTO;
import com.ufrn.SIGZoo.service.VeterinarioService;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("")
    public ResponseEntity<List<VeterinarioDTO>> listarTodosVeterinarios() {
        List<VeterinarioDTO> veterinarios = veterinarioService.listarTodos();
        return ResponseEntity.ok(veterinarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDTO> buscarVeterinarioPorId(@PathVariable Integer id) {
        VeterinarioDTO veterinario = veterinarioService.buscarPorId(id);

        return ResponseEntity.ok(veterinario);
    }

    @GetMapping("/listarPorQtdPacientes")
    public ResponseEntity<List<VeterinarioDTO>> listarVeterinariosPorQtdPacientes() {
        List<VeterinarioDTO> veterinarios = veterinarioService.listarPorQtdPacientes();
        return ResponseEntity.ok(veterinarios);
    }

    @PostMapping("/criar")
    public ResponseEntity<VeterinarioDTO> cadastrarVeterinario(@RequestBody VeterinarioDTO veterinario) {
        VeterinarioDTO novoVeterinario = veterinarioService.criar(veterinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeterinario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VeterinarioDTO> atualizarVeterinario(@PathVariable Integer id, @RequestBody VeterinarioDTO veterinario) {
        VeterinarioDTO vetAtualizado = veterinarioService.atualizar(id, veterinario);
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
