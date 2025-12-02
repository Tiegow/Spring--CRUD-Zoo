package com.ufrn.SIGZoo.controller.api;

import java.time.LocalDate;
import java.util.List;

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
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    // Listar todas paginadas
    @GetMapping("")
    public ResponseEntity<Page<OrdemServicoDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "dataInicio") Pageable pageable) {

        Page<OrdemServicoDTO> ordens = ordemServicoService.listarTodos(pageable);
        return ResponseEntity.ok(ordens);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> buscarPorId(@PathVariable Integer id) {
        OrdemServicoDTO dto = ordemServicoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    // Criar ordem de serviço
    @PostMapping("/criar")
    public ResponseEntity<OrdemServicoDTO> criar(@RequestBody OrdemServicoDTO dto) {
        OrdemServicoDTO nova = ordemServicoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    // Atualizar ordem de serviço
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<OrdemServicoDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody OrdemServicoDTO dto) {

        OrdemServicoDTO atualizada = ordemServicoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // Deletar ordem de serviço
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        ordemServicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por funcionário
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<OrdemServicoDTO>> listarPorFuncionario(
            @PathVariable Integer funcionarioId) {

        List<OrdemServicoDTO> ordens = ordemServicoService.buscarPorFuncionario(funcionarioId);
        return ResponseEntity.ok(ordens);
    }

    // Buscar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrdemServicoDTO>> listarPorStatus(@PathVariable String status) {
        List<OrdemServicoDTO> ordens = ordemServicoService.buscarPorStatus(status);
        return ResponseEntity.ok(ordens);
    }

    // Buscar por intervalo de datas
    @GetMapping("/periodo")
    public ResponseEntity<List<OrdemServicoDTO>> listarPorPeriodo(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<OrdemServicoDTO> ordens = ordemServicoService.buscarPorIntervaloDeData(inicio, fim);
        return ResponseEntity.ok(ordens);
    }
}
