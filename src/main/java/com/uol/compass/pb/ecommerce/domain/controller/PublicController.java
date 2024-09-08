package com.uol.compass.pb.ecommerce.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.pb.ecommerce.domain.service.UsuarioService;

@RestController
public class PublicController {
	private final UsuarioService usuarioService;

	public PublicController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PatchMapping("/public/resetarsenha")
	public ResponseEntity<String> resetarSenha(@RequestBody String login, String senha) {
		usuarioService.resetarSenha(login, senha);
		return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada.");
	}
}
