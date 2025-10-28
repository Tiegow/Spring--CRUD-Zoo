package com.ufrn.SIGZoo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.SIGZoo.model.dto.EspecieDTO;
import com.ufrn.SIGZoo.service.EspecieService;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/especies")
public class EspecieController {
    @Autowired
    private EspecieService especieService;

    
    @PostMapping("/criar")
    public ResponseEntity<EspecieDTO> criar(@RequestBody EspecieDTO dto) {
        EspecieDTO especieNova = especieService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(especieNova);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<EspecieDTO> criar(@PathVariable Integer id) {
        especieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EspecieDTO> atualizar(@RequestBody EspecieDTO dto, @PathVariable Integer id) {
        EspecieDTO especieAtualizada = especieService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(especieAtualizada);
    }
    
    
    @GetMapping("")
    public ResponseEntity<Page<EspecieDTO>> listarTodos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<EspecieDTO> especies = especieService.listarTodos(pageable);
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/expectativa_vida_maior_que/{idade}")
    public ResponseEntity<List<EspecieDTO>> listarPorExpectativaVidaMaiorQue(@PathVariable Integer idade) {
        List<EspecieDTO> listaEspecies = especieService.buscarPorExpectativaVidaMaiorQue(idade);
        return ResponseEntity.ok(listaEspecies);
    }

    @GetMapping("/expectativa_vida_menor_que/{idade}")
    public ResponseEntity<List<EspecieDTO>> listarPorExpectativaVidaMenorQue(@PathVariable Integer idade) {
        List<EspecieDTO> listaEspecies = especieService.buscarPorExpectativaVidaMenorQue(idade);
        return ResponseEntity.ok(listaEspecies);
    }
}
