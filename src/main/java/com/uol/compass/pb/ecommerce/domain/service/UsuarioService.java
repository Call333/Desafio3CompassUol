package com.uol.compass.pb.ecommerce.domain.service;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
	
	public Usuario updateUsuario(Long id, Usuario usuario){
		Optional<Usuario> usuarioEncontrado = searchById(id);
		Usuario modelo = usuarioEncontrado.get();
		modelo.setNome(usuario.getNome());
		modelo.setSobrenome(usuario.getSobrenome());
		modelo.setCPF(usuario.getCPF());
		modelo.setEndereco(usuario.getEndereco());
		modelo.setData_nascimento(usuario.getData_nascimento());

		return usuarioRepository.save(modelo);
	} 
	
	public void deleteUsuarioById(Long id){
		usuarioRepository.deleteById(id);
	}
}
