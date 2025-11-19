package com.ufrn.SIGZoo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.SIGZoo.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
