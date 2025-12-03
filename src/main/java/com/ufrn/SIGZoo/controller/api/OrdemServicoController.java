package com.ufrn.SIGZoo.controller.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufrn.SIGZoo.model.dto.OrdemServicoDTO;
import com.ufrn.SIGZoo.service.OrdemServicoService;

@RestController
@RequestMapping("/api/ordensServico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping
    public ResponseEntity<Page<OrdemServicoDTO>> filtrar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fim,
            Pageable pageable
    ) {

        if (status != null) {
            return ResponseEntity.ok(ordemServicoService.listarPorStatusPaginado(status, pageable));
        }

        if (inicio != null && fim != null) {
            return ResponseEntity.ok(ordemServicoService.listarPorPeriodoPaginado(inicio, fim, pageable));
        }

        return ResponseEntity.ok(ordemServicoService.listarTodos(pageable));
    }


    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ordemServicoService.buscarPorId(id));
    }

    // Criar
    @PostMapping("/criar")
    public ResponseEntity<OrdemServicoDTO> criar(@RequestBody OrdemServicoDTO dto) {
        OrdemServicoDTO nova = ordemServicoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    // Atualizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<OrdemServicoDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody OrdemServicoDTO dto) {

        return ResponseEntity.ok(ordemServicoService.atualizar(id, dto));
    }

    // Deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        ordemServicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
