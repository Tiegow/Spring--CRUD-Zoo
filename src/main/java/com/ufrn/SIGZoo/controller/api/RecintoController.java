package com.ufrn.SIGZoo.controller.api;

import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.service.RecintoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recintos")
@Tag(name = "Recintos", description = "Endpoints para gerenciamento de recintos")
public class RecintoController {

    @Autowired
    private RecintoService recintoService;

    @Operation(summary = "Cria um novo recinto")
    @PostMapping
    public ResponseEntity<RecintoDTO> criarRecinto(@Valid @RequestBody RecintoDTO recintoDTO) {
        RecintoDTO novoRecinto = recintoService.criar(recintoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRecinto);
    }

    @Operation(summary = "Lista todos os recintos de forma paginada")
    @GetMapping
    public ResponseEntity<Page<RecintoDTO>> listarRecintos(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<RecintoDTO> paginaDeRecintos = recintoService.listarTodos(pageable);
        return ResponseEntity.ok(paginaDeRecintos);
    }

    @Operation(summary = "Busca um recinto pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<RecintoDTO> buscarRecintoPorId(@PathVariable Integer id) {
        RecintoDTO recinto = recintoService.buscarPorId(id);
        return ResponseEntity.ok(recinto);
    }

    @Operation(summary = "Atualiza um recinto existente")
    @PutMapping("/{id}")
    public ResponseEntity<RecintoDTO> atualizarRecinto(@PathVariable Integer id, @Valid @RequestBody RecintoDTO recintoDTO) {
        RecintoDTO recintoAtualizado = recintoService.atualizar(id, recintoDTO);
        return ResponseEntity.ok(recintoAtualizado);
    }

    @Operation(summary = "Deleta um recinto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecinto(@PathVariable Integer id) {
        recintoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filtra recintos por área")
    @GetMapping("/area")
    public ResponseEntity<List<RecintoDTO>> filtroArea(
            @RequestParam Optional<Float> minimo,
            @RequestParam Optional<Float> maximo
    ) {
        List<RecintoDTO> lista = recintoService.buscarPorArea(minimo.orElse(null), maximo.orElse(null));
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Filtra recintos por população")
    @GetMapping("/populacao")
    public ResponseEntity<List<RecintoDTO>> filtroPopulacao(
            @RequestParam Optional<Integer> minimo,
            @RequestParam Optional<Integer> maximo
    ) {
        List<RecintoDTO> lista = recintoService.buscarPorPopulacao(minimo.orElse(null), maximo.orElse(null));
        return ResponseEntity.ok(lista);
    }

}
