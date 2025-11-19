package com.ufrn.SIGZoo.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufrn.SIGZoo.model.entity.Usuario;
import com.ufrn.SIGZoo.service.UsuarioService;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro"; 
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.cadastrarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado! Faça login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/cadastro";
        }
    }
}
