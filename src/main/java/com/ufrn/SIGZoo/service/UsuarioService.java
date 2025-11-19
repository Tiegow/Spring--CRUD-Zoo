package com.ufrn.SIGZoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufrn.SIGZoo.model.entity.Usuario;
import com.ufrn.SIGZoo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public void cadastrarUsuario(Usuario usuario) throws Exception {

        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new Exception("Este email já está cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        if (usuario.getPerfil() == null) {
            usuario.setPerfil(Usuario.PerfilUsuario.ADMIN);
        }

        repository.save(usuario);
    }
}
