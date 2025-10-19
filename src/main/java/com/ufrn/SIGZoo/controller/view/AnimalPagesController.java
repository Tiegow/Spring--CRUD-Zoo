package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.model.dto.AnimalDTO;
import com.ufrn.SIGZoo.service.AnimalService;
import com.ufrn.SIGZoo.service.VeterinarioService;

@Controller
@RequestMapping("/animais")
public class AnimalPagesController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("")
    public String animaisHome(Model model) {
        model.addAttribute("animais", animalService.listarTodos());
        return "animais/home";
    }

    @GetMapping("/novo")
    public String novoAnimal(Model model) {
        model.addAttribute("veterinarios", veterinarioService.listarTodos());

        return "animais/novo";
    }

    @GetMapping("/{id}")
    public String detalhesAnimal(@PathVariable Integer id, Model model) {
        AnimalDTO animal = animalService.buscarPorId(id);

        model.addAttribute("animal", animal);

        return "animais/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarAnimal(@PathVariable Integer id, Model model) {
        AnimalDTO animal = animalService.buscarPorId(id);

        model.addAttribute("animal", animal);
        model.addAttribute("veterinarios", veterinarioService.listarTodos());
        
        return "animais/editar";
    }
}
