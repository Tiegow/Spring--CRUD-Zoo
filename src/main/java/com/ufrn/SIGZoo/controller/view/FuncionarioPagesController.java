package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioPagesController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public String funcionariosHome(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "funcionarios/home";
    }
}
