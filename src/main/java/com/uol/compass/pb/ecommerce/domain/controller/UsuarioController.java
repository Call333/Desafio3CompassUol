package com.uol.compass.pb.ecommerce.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.service.UsuarioService;
import com.uol.compass.pb.ecommerce.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
		
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO body ){
		Usuario usuarioCriado = usuarioService.createUsuario(body.getUsuario(), body.getPermissoes());
		UsuarioDTO modeloDto = new UsuarioDTO();
		modeloDto.setUsuario(usuarioCriado);
		modeloDto.setPermissoes(body.getPermissoes());
		return ResponseEntity.status(HttpStatus.CREATED).body(modeloDto);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN','USER')")
	public ResponseEntity<List<Usuario>> encontrarUsuarios(){
		List<Usuario> usuariosEncontrados = usuarioService.searchAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(usuariosEncontrados);
	}
	
	@GetMapping(path = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Optional<Usuario>> encontrarUsuarioPeloId(@PathVariable Long id){
		Optional<Usuario> usuarioEncontrado = usuarioService.searchById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(usuarioEncontrado);
	}
	
	@PutMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO body){
		Usuario atualizado = usuarioService.updateUsuario(id, body.getUsuario(), body.getPermissoes());
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}
	
	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletarUsuario(@PathVariable Long id){
		usuarioService.deleteUsuarioById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
	}
}
