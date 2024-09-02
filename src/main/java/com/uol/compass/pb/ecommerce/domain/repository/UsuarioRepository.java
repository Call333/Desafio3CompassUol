package com.uol.compass.pb.ecommerce.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByLogin(String login);
} 
