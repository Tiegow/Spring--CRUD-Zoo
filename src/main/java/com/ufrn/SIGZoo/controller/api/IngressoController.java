package com.ufrn.SIGZoo.controller.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufrn.SIGZoo.model.dto.IngressoDTO;
import com.ufrn.SIGZoo.service.IngressoService;

@RestController
@RequestMapping("/api/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @GetMapping
    public ResponseEntity<Page<IngressoDTO>> filtrar(
            @RequestParam(required = false) Double min,   // filtrar custo mínimo
            @RequestParam(required = false) Double max,   // filtrar custo máximo

            @RequestParam(required = false) LocalDate compraInicio,
            @RequestParam(required = false) LocalDate compraFim,

            @RequestParam(required = false) LocalDate visitaInicio,
            @RequestParam(required = false) LocalDate visitaFim,

            Pageable pageable
    ) {

        if (min != null && max != null) {
            return ResponseEntity.ok(
                ingressoService.filtrarPorCusto(min, max, pageable)
            );
        }

        if (compraInicio != null && compraFim != null) {
            return ResponseEntity.ok(
                ingressoService.filtrarPorDataCompra(compraInicio, compraFim, pageable)
            );
        }

        if (visitaInicio != null && visitaFim != null) {
            return ResponseEntity.ok(
                ingressoService.filtrarPorDataVisita(visitaInicio, visitaFim, pageable)
            );
        }

        return ResponseEntity.ok(ingressoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngressoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ingressoService.buscarPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<IngressoDTO> criar(@RequestBody IngressoDTO dto) {
        IngressoDTO novo = ingressoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<IngressoDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody IngressoDTO dto) {

        return ResponseEntity.ok(ingressoService.atualizar(id, dto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        ingressoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultar")
    public ResponseEntity<IngressoDTO> consultarIngresso(
            @RequestParam Integer id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataVisita) {
        
        IngressoDTO ingresso = ingressoService.buscarPorId(id);
        
        if (ingresso != null && ingresso.getDataVisita().equals(dataVisita)) {
            return ResponseEntity.ok(ingresso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
