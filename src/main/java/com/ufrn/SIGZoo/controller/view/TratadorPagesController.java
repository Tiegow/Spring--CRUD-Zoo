package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.SIGZoo.model.dto.TratadorDTO;
import com.ufrn.SIGZoo.service.TratadorService;


@Controller
@RequestMapping("/funcionarios/tratador")
public class TratadorPagesController {

    @Autowired 
    private TratadorService tratadorService;

    @GetMapping("/{id}")
    public String detalhesTratador(@PathVariable Integer id, Model model) {
        TratadorDTO trat = tratadorService.buscarPorId(id);
        model.addAttribute("tratador", trat);

        return "funcionarios/tratador/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarTratador(@PathVariable Integer id, Model model) {
        TratadorDTO trat = tratadorService.buscarPorId(id);
        model.addAttribute("tratador", trat);

        return "funcionarios/tratador/editar";
    }
}
