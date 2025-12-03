package com.ufrn.SIGZoo.controller.view;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ufrn.SIGZoo.model.dto.OrdemServicoDTO;
import com.ufrn.SIGZoo.service.FuncionarioService;
import com.ufrn.SIGZoo.service.OrdemServicoService;

@Controller
@RequestMapping("/ordensServico")
public class OrdemServicoPagesController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private FuncionarioService funcionarioService;

    // LISTAGEM + FILTROS
    @GetMapping("")
    public String ordensHome(
            Model model,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            @PageableDefault(size = 10, sort = "dataInicio") Pageable pageable
    ) {
        Page<OrdemServicoDTO> pagina;

        if (status != null && !status.isEmpty()) {
            pagina = ordemServicoService.listarPorStatusPaginado(status, pageable);
        }
        else if (inicio != null && fim != null) {
            pagina = ordemServicoService.listarPorPeriodoPaginado(inicio, fim, pageable);
        }
        else {
            pagina = ordemServicoService.listarTodos(pageable);
        }

        model.addAttribute("ordens", pagina);
        return "ordensServico/home";
    }

    // NOVA ORDEM
    @GetMapping("/nova")
    public String novaOrdem(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "ordensServico/nova";
    }

    // DETALHES
    @GetMapping("/{id}")
    public String detalhes(@PathVariable Integer id, Model model) {
        OrdemServicoDTO dto = ordemServicoService.buscarPorId(id);
        model.addAttribute("os", dto);
        return "ordensServico/detalhes";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editarOrdem(@PathVariable Integer id, Model model) {

        OrdemServicoDTO ordem = ordemServicoService.buscarPorId(id);
        model.addAttribute("os", ordem);
        model.addAttribute("funcionarios", funcionarioService.listarTodos());

        return "ordensServico/editar";
    }
}
