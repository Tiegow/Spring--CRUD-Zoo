package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.service.VeterinarioService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/funcionarios/veterinario")
public class VeterinarioPagesController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("/{id}")
    public String detalhesVeterinario(@PathVariable Integer id, Model model) {
        Veterinario vet = veterinarioService.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        model.addAttribute("veterinario", vet);

        return "funcionarios/veterinario/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarVeterinario(@PathVariable Integer id, Model model) {
        Veterinario vet = veterinarioService.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        model.addAttribute("veterinario", vet);

        return "funcionarios/veterinario/editar";
    }

    @GetMapping("/atribuir/{id}")
    public String atribuirPaciente(@PathVariable Integer id, Model model) {
        Veterinario vet = veterinarioService.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        model.addAttribute("veterinario", vet);

        return "funcionarios/veterinario/atribuir";
    }
}
