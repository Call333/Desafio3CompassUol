package com.uol.compass.pb.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.entities.UsuarioGrupo;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String>{
	
	@Query("""
			
			select distinct g.nome
            from UsuarioGrupo ug
            join ug.grupo g
            join ug.usuario u
            where u = ?1
			
			""")
	List<String> findPermissoesByUsuario(Usuario usuario);
	
}
