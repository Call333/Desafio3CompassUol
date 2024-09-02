package com.uol.compass.pb.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uol.compass.pb.ecommerce.domain.entities.GrupoUsuario;
import com.uol.compass.pb.ecommerce.domain.entities.Usuario;

public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Long>{
	@Query("""
			
			select distinct g.nome
			from GrupoUsuario gu
			join gu.grupo g
			join gu.usuario u 
			where u = ?1
			
			""")
	List<String> findPermissoesByUsuario(Usuario usuario); 
}
