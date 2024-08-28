package com.uol.compass.pb.ecommerce.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioService usuarioService;
		
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
		Usuario usuarioCriado = usuarioService.createUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> encontrarUsuarios(){
		List<Usuario> usuariosEncontrados = usuarioService.searchAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(usuariosEncontrados);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Usuario>> encontrarUsuarioPeloId(@PathVariable Long id){
		Optional<Usuario> usuarioEncontrado = usuarioService.searchById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(usuarioEncontrado);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
		Usuario atualizado = usuarioService.updateUsuario(id, usuario);
		return ResponseEntity.status(HttpStatus.OK).body(atualizado);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deletarUsuario(@PathVariable Long id){
		usuarioService.deleteUsuarioById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
	}
}
