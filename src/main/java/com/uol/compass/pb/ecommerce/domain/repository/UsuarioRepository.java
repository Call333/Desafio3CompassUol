package com.uol.compass.pb.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
