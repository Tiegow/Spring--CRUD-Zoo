package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.service.EspecieService;

@Controller
@RequestMapping("/especies")
public class EspeciePagesController {
    
    @Autowired
    private EspecieService especieService;

    @GetMapping("")
    public String especiesHome(Model model, @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        model.addAttribute("especies", especieService.listarTodos(pageable));
        return "especies/home";
    }

    @GetMapping("/novo")
    public String novoEspecie() {
        return "especies/novo";
    }
}
