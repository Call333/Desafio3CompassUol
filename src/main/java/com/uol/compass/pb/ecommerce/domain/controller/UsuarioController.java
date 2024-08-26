package com.uol.compass.pb.ecommerce.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<String> createUser(@RequestBody Usuario usuario){
		Usuario usuarioCriado = usuarioService.createUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body("Criado o usuario");
	}
}
