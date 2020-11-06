package com.leandrorocha.wkusuariosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandrorocha.wkusuariosbackend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
