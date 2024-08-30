package com.uol.compass.pb.ecommerce.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.pb.ecommerce.domain.entities.Grupo;
import com.uol.compass.pb.ecommerce.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	public final GrupoService grupoService;
	
	public GrupoController(GrupoService grupoService) {
		this.grupoService = grupoService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Grupo> criarGrupo(@RequestBody Grupo grupo) {
		Grupo grupoCriado = grupoService.saveGrupo(grupo);
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoCriado);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<Grupo>> encontrarTodosGrupo() {
		List<Grupo> grupos = grupoService.searchAllGrupo();
		return ResponseEntity.status(HttpStatus.CREATED).body(grupos);
	}
	
	@GetMapping(path = "/{id}" )
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Grupo> encontrarGrupoPeloId(@PathVariable Long id) {
		Grupo grupo = grupoService.searchGrupoById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
	}
	
	@PutMapping(path = "/{id}" )
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Grupo> atualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
		Grupo grupoAtualizado = grupoService.updateGrupo(id, grupo);
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoAtualizado);
	}
	
	@DeleteMapping(path = "/{id}" )
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletarGrupo(@PathVariable Long id) {
		grupoService.deleteGrupo(id);
		return ResponseEntity.status(HttpStatus.CREATED).body("Grupo deletado");
	}
}
