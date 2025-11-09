package com.ufrn.SIGZoo.controller.api;

<<<<<<< Updated upstream
import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.service.RecintoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

=======
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
@RestController
@RequestMapping("/api/recintos")
@Tag(name = "Recintos", description = "Endpoints para gerenciamento de recintos")
=======
import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.service.RecintoService;

@RestController
@RequestMapping("/api/recintos")
>>>>>>> Stashed changes
public class RecintoController {

    @Autowired
    private RecintoService recintoService;

<<<<<<< Updated upstream
    @Operation(summary = "Cria um novo recinto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recinto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @PostMapping
    public ResponseEntity<RecintoDTO> criarRecinto(@Valid @RequestBody RecintoDTO recintoDTO) {
        RecintoDTO novoRecinto = recintoService.criar(recintoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRecinto);
    }

    @Operation(summary = "Lista todos os recintos de forma paginada")
    @ApiResponse(responseCode = "200", description = "Lista de recintos retornada")
    @GetMapping
    public ResponseEntity<Page<RecintoDTO>> listarRecintos(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<RecintoDTO> paginaDeRecintos = recintoService.listarTodos(pageable);
        return ResponseEntity.ok(paginaDeRecintos);
    }

    @Operation(summary = "Busca um recinto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recinto encontrado"),
        @ApiResponse(responseCode = "404", description = "Recinto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RecintoDTO> buscarRecintoPorId(@PathVariable Integer id) {
        RecintoDTO recinto = recintoService.buscarPorId(id);
        return ResponseEntity.ok(recinto);
    }

    @Operation(summary = "Atualiza um recinto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recinto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Recinto não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RecintoDTO> atualizarRecinto(@PathVariable Integer id, @Valid @RequestBody RecintoDTO recintoDTO) {
        RecintoDTO recintoAtualizado = recintoService.atualizar(id, recintoDTO);
        return ResponseEntity.ok(recintoAtualizado);
    }

    @Operation(summary = "Deleta um recinto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recinto deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Recinto não encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflito (ex: recinto não está vazio)")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecinto(@PathVariable Integer id) {
        recintoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
=======
    @PostMapping("/criar")
    public ResponseEntity<RecintoDTO> criar(@RequestBody RecintoDTO dto) {
        RecintoDTO recintoNovo = recintoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recintoNovo);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        recintoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<RecintoDTO> atualizar(@RequestBody RecintoDTO dto, @PathVariable Integer id) {
        RecintoDTO recintoAtualizado = recintoService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recintoAtualizado);
    }

    @GetMapping("")
    public ResponseEntity<Page<RecintoDTO>> listarTodos(
        @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        Page<RecintoDTO> recintos = recintoService.listarTodos(pageable);
        return ResponseEntity.ok(recintos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecintoDTO> buscarPorId(@PathVariable Integer id) {
        RecintoDTO dto = recintoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }
}
>>>>>>> Stashed changes
