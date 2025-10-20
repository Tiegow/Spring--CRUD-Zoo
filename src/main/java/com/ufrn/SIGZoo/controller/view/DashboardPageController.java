package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.service.AnimalService;
import com.ufrn.SIGZoo.service.FuncionarioService;

@Controller
@RequestMapping("")
public class DashboardPageController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public String dashboardPage(Model model) {
        model.addAttribute("qtdAnimais", animalService.obterQtdAnimais());
        model.addAttribute("qtdFuncionarios", funcionarioService.obterQtdFuncionarios());

        return "home";
    }
}
