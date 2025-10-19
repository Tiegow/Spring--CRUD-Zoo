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

import com.ufrn.SIGZoo.model.dto.AnimalDTO;
import com.ufrn.SIGZoo.service.AnimalService;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("")
    public ResponseEntity<List<AnimalDTO>> listarTodos() {
        List<AnimalDTO> animais = animalService.listarTodos();
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/sexo/{sexo}")
    public ResponseEntity<List<AnimalDTO>> listarPorSexo(@PathVariable String sexo) {
        List<AnimalDTO> animais = animalService.listarPorSexo(sexo);
        return ResponseEntity.ok(animais);
    }

    @PostMapping("/criar")
    public ResponseEntity<AnimalDTO> criarAnimal(@RequestBody AnimalDTO dto) {
        AnimalDTO novoAnimal = animalService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Integer id) {
        animalService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AnimalDTO> atualizarAnimal(@PathVariable Integer id, @RequestBody AnimalDTO dto) {
        AnimalDTO animalAtualizado = animalService.atualizar(id, dto);
        if (animalAtualizado != null) {
            return ResponseEntity.ok(animalAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
