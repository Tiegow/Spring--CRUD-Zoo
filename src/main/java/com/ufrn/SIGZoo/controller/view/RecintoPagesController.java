package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.model.dto.RecintoDTO;
import com.ufrn.SIGZoo.service.PlanoDietaService;
import com.ufrn.SIGZoo.service.RecintoService;
import com.ufrn.SIGZoo.service.TratadorService;

@Controller
@RequestMapping("/recintos")
public class RecintoPagesController {

    @Autowired
    private RecintoService recintoService;

    @Autowired
    private PlanoDietaService planoDietaService; 

    @Autowired
    private TratadorService tratadorService;     

    @GetMapping("")
    public String recintosHome(Model model, @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        model.addAttribute("recintos", recintoService.listarTodos(pageable));
        return "recintos/home";
    }



    @GetMapping("/novo")
    public String novoRecinto(Model model) {
        model.addAttribute("planosDieta", planoDietaService.listarTodos());
        model.addAttribute("tratadores", tratadorService.listarTodos());
        
        return "recintos/novo";
    }

    @GetMapping("/{id}")
    public String detalhesRecinto(@PathVariable Integer id, Model model) {
        RecintoDTO recinto = recintoService.buscarPorId(id);
        model.addAttribute("recinto", recinto);
        
        return "recintos/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarRecinto(@PathVariable Integer id, Model model) {
        RecintoDTO recinto = recintoService.buscarPorId(id);
        model.addAttribute("recinto", recinto);

        model.addAttribute("planosDieta", planoDietaService.listarTodos());
        model.addAttribute("tratadores", tratadorService.listarTodos());

        return "recintos/editar";
    }

}
