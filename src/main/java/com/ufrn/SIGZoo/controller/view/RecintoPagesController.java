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
<<<<<<< Updated upstream
import com.ufrn.SIGZoo.service.PlanoDietaService;
import com.ufrn.SIGZoo.service.RecintoService;
import com.ufrn.SIGZoo.service.TratadorService;
=======
import com.ufrn.SIGZoo.service.RecintoService;
>>>>>>> Stashed changes

@Controller
@RequestMapping("/recintos")
public class RecintoPagesController {
<<<<<<< Updated upstream

    @Autowired
    private RecintoService recintoService;

    @Autowired
    private PlanoDietaService planoDietaService; 

    @Autowired
    private TratadorService tratadorService;     

    @GetMapping("")
    public String recintosHome(Model model, @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        model.addAttribute("recintos", recintoService.listarTodos(pageable));
=======
    
    @Autowired
    private RecintoService recintosService;

    @GetMapping("")
    public String recintosHome(Model model, @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        model.addAttribute("recintos", recintosService.listarTodos(pageable));
>>>>>>> Stashed changes
        return "recintos/home";
    }

    @GetMapping("/novo")
<<<<<<< Updated upstream
    public String novoRecinto(Model model) {
        model.addAttribute("planosDieta", planoDietaService.listarTodos());
        model.addAttribute("tratadores", tratadorService.listarTodos());
        
=======
    public String novoRecinto() {
>>>>>>> Stashed changes
        return "recintos/novo";
    }

    @GetMapping("/{id}")
    public String detalhesRecinto(@PathVariable Integer id, Model model) {
<<<<<<< Updated upstream
        RecintoDTO recinto = recintoService.buscarPorId(id);
        model.addAttribute("recinto", recinto);
        
=======
        RecintoDTO recinto = recintosService.buscarPorId(id);
        model.addAttribute("recinto", recinto);
>>>>>>> Stashed changes
        return "recintos/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarRecinto(@PathVariable Integer id, Model model) {
<<<<<<< Updated upstream
        RecintoDTO recinto = recintoService.buscarPorId(id);
        model.addAttribute("recinto", recinto);

        model.addAttribute("planosDieta", planoDietaService.listarTodos());
        model.addAttribute("tratadores", tratadorService.listarTodos());
        
        return "recintos/editar";
    }
}
=======
        RecintoDTO recinto = recintosService.buscarPorId(id);
        model.addAttribute("recinto", recinto);
        
        return "recintos/editar";
    }
}
>>>>>>> Stashed changes
