package com.uol.compass.pb.ecommerce.domain.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario createUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> searchAll(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> searchById(Long id){
		return usuarioRepository.findById(id);
	}
}
