package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufrn.SIGZoo.model.dto.EventoDTO;
import com.ufrn.SIGZoo.service.EventoService;
import com.ufrn.SIGZoo.service.RecintoService;

@Controller
@RequestMapping("/eventos")
public class EventoPagesController {
    
    @Autowired
    private EventoService eventoService;

    @Autowired
    private RecintoService recintoService;

    @GetMapping("")
    public String eventosHome(
        @RequestParam(required = false) Integer minCap,
        @RequestParam(required = false) Integer maxCap,
        Model model,
        Pageable pageable
    ){
        Page<EventoDTO> eventos = eventoService.listarPorCapacidade(pageable, minCap, maxCap);
        model.addAttribute("eventos", eventos);

        return "eventos/home";
    }


    @GetMapping("/novo")
    public String novoEvento(Model model) {
        model.addAttribute("recintos", recintoService.listarTodos());
        
        return "eventos/novo";
    }

    @GetMapping("/{id}")
    public String detalhesEvento(@PathVariable Integer id, Model model) {
        EventoDTO evento = eventoService.buscarPorId(id);
        model.addAttribute("evento", evento);
        model.addAttribute("recintos", recintoService.listarTodos());
        return "eventos/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable Integer id, Model model) {
        EventoDTO evento = eventoService.buscarPorId(id);
        model.addAttribute("evento", evento);   
        model.addAttribute("recintos", recintoService.listarTodos());
        return "eventos/editar";
    }
}
