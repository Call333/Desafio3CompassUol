package com.uol.compass.pb.ecommerce.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.pb.ecommerce.domain.entities.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	Optional<Grupo> findByNome(String nome);
}
