package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.model.dto.OrdemServicoDTO;
import com.ufrn.SIGZoo.service.FuncionarioService;
import com.ufrn.SIGZoo.service.OrdemServicoService;

@Controller
@RequestMapping("/ordens-servico")
public class OrdemServicoPagesController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private FuncionarioService funcionarioService;

    // Página inicial com lista paginada de ordens
    @GetMapping("")
    public String ordensHome(Model model, 
            @PageableDefault(size = 10, sort = "dataInicio") Pageable pageable) {

        model.addAttribute("ordens", ordemServicoService.listarTodos(pageable));
        return "ordemServico/home";
    }

    // Página para criar nova ordem
    @GetMapping("/nova")
    public String novaOrdem(Model model) {

        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "ordemServico/nova";
    }

    // Detalhes de uma ordem específica
    @GetMapping("/{id}")
    public String detalhesOrdem(@PathVariable Integer id, Model model) {

        OrdemServicoDTO ordem = ordemServicoService.buscarPorId(id);
        model.addAttribute("ordem", ordem);

        return "ordemServico/detalhes";
    }

    // Página para editar uma ordem já existente
    @GetMapping("/editar/{id}")
    public String editarOrdem(@PathVariable Integer id, Model model) {

        OrdemServicoDTO ordem = ordemServicoService.buscarPorId(id);
        model.addAttribute("ordem", ordem);
        model.addAttribute("funcionarios", funcionarioService.listarTodos());

        return "ordemServico/editar";
    }
}
