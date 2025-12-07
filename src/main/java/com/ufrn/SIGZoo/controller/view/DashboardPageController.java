package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.service.AnimalService;
import com.ufrn.SIGZoo.service.EspecieService;
import com.ufrn.SIGZoo.service.EventoService;
import com.ufrn.SIGZoo.service.FuncionarioService;
import com.ufrn.SIGZoo.service.IngressoService;
import com.ufrn.SIGZoo.service.OrdemServicoService;
import com.ufrn.SIGZoo.service.RecintoService;

@Controller
@RequestMapping("")
public class DashboardPageController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private RecintoService recintoService;

    @Autowired
    private EspecieService especieService;

    @Autowired
    private EventoService eventoService;

    @Autowired 
    private OrdemServicoService ordemServicoService;

    @Autowired
    private IngressoService ingressoService; 

    @GetMapping("")
    public String dashboardPage(Model model) {
        model.addAttribute("qtdAnimais", animalService.obterQtdAnimais());
        model.addAttribute("qtdFuncionarios", funcionarioService.obterQtdFuncionarios());
        model.addAttribute("qtdRecintos", recintoService.obterQtdRecintos());
        model.addAttribute("qtdEspecies", especieService.obterQtdEspecies());
        model.addAttribute("qtdEventos", eventoService.obterQtdEventos());
        model.addAttribute("qtdOrdensServico", ordemServicoService.obterQtdOrdensServico());
        model.addAttribute("qtdIngressos", ingressoService.obterQtdIngressos());
        
        return "home";
    }
}
