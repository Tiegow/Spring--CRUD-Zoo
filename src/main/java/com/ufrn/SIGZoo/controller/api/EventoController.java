package com.ufrn.SIGZoo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufrn.SIGZoo.model.dto.EventoDTO;
import com.ufrn.SIGZoo.service.EventoService;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/criar")
    public ResponseEntity<EventoDTO> criar(@RequestBody EventoDTO dto) {
        EventoDTO novoEvento = eventoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EventoDTO> atualizar(@RequestBody EventoDTO dto, @PathVariable Integer id) {
        EventoDTO eventoAtualizado = eventoService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoAtualizado);
    }

    @GetMapping("")
    public ResponseEntity<Page<EventoDTO>> listarTodos(
        @PageableDefault(size = 10, sort = "data") Pageable pageable
    ) {
        Page<EventoDTO> eventos = eventoService.listarTodos(pageable);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Integer id) {
        EventoDTO dto = eventoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }
}
