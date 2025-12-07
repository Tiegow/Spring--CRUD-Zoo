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

import com.ufrn.SIGZoo.model.dto.IngressoDTO;
import com.ufrn.SIGZoo.service.IngressoService;

@Controller
@RequestMapping("/ingressos")
public class IngressoPagesController {

    @Autowired
    private IngressoService ingressoService;

    @GetMapping("")
    public String ingressosHome(
            Model model,

            // Filtro por preço
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,

            // Filtro por data de compra
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioCompra,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fimCompra,

            // Filtro por data de visita
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioVisita,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fimVisita,

            @PageableDefault(size = 10, sort = "dataCompra") Pageable pageable
    ) {
        Page<IngressoDTO> pagina;

        if (precoMin != null && precoMax != null) {
            pagina = ingressoService.filtrarPorCusto(precoMin, precoMax, pageable);
        }
        else if (inicioCompra != null && fimCompra != null) {
            pagina = ingressoService.filtrarPorDataCompra(inicioCompra, fimCompra, pageable);
        }
        else if (inicioVisita != null && fimVisita != null) {
            pagina = ingressoService.filtrarPorDataVisita(inicioVisita, fimVisita, pageable);
        }
        else {
            pagina = ingressoService.listarTodos(pageable);
        }

        model.addAttribute("ingressos", pagina);
        return "ingressos/home";
    }

    @GetMapping("/novo")
    public String novoIngresso(Model model) {
        model.addAttribute("ingresso", new IngressoDTO()); 
        return "ingressos/novo";
    }

    // @GetMapping("/{id}")
    // public String detalhes(@PathVariable Integer id, Model model) {
    //     IngressoDTO dto = ingressoService.buscarPorId(id);
    //     model.addAttribute("ingresso", dto);
    //     return "ingressos/detalhes";
    // }

    @GetMapping("/editar/{id}")
    public String editarIngresso(@PathVariable Integer id, Model model) {
        IngressoDTO dto = ingressoService.buscarPorId(id);
        model.addAttribute("ingresso", dto);
        return "ingressos/editar";
    }
}

