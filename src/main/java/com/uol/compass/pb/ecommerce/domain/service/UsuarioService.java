package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uol.compass.pb.ecommerce.domain.entities.Grupo;
import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.entities.UsuarioGrupo;
import com.uol.compass.pb.ecommerce.domain.repository.GrupoRepository;
import com.uol.compass.pb.ecommerce.domain.repository.UsuarioGrupoRepository;
import com.uol.compass.pb.ecommerce.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final GrupoRepository grupoRepository;
	private final UsuarioGrupoRepository grupoUsuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioService(
			UsuarioRepository usuarioRepository, 
			GrupoRepository grupoRepository,
			UsuarioGrupoRepository grupoUsuarioRepository, 
			PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.grupoRepository = grupoRepository;
		this.grupoUsuarioRepository = grupoUsuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public Usuario createUsuario(Usuario usuario, List<String> grupos) {
		String senhaCriptografa = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografa);
		usuarioRepository.save(usuario);
		
		/*
		 * Código que lê a lista de grupos que vem do corpo da requisição
		 * e verifica se esses usuarios possuem o grupo que exista na tabela
		 * "grupo" no banco de dados. Assim que constata que um ou mais grupos
		 * existem na tabela, ele cria um objeto do tipo UsuarioGrupo(junção do
		 * Usuario e o Grupo) e salva esse objeto numa tabela de usuarios com grupos de
		 * usuarios com permissões.
		 * */
		
		List<UsuarioGrupo> listaGrupoUsuario = grupos.stream()
				.map(nomeGrupo -> {
					Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
					
					if(possivelGrupo.isPresent()) {
						Grupo grupo = possivelGrupo.get();
						return new UsuarioGrupo(usuario, grupo);
					}
					return null;
				})
				.filter(grupo -> grupo != null)
				.collect(Collectors.toList());
		
		grupoUsuarioRepository.saveAll(listaGrupoUsuario);
		
		return usuario;
	}
	
	public List<Usuario> searchAll(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> searchById(Long id){
		return usuarioRepository.findById(id);
	}
	
	public Usuario updateUsuario(Long id, Usuario usuario, List<String> grupos ){
		Optional<Usuario> usuarioEncontrado = searchById(id);
		
		Usuario modelo = usuarioEncontrado.get();
		modelo.setLogin(usuario.getLogin());
		modelo.setSenha(usuario.getSenha());
		modelo.setNome(usuario.getNome());
		
		List<UsuarioGrupo> listaGrupoUsuario = grupos.stream()
				.map(nomeGrupo -> {
					Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
					
					if(possivelGrupo.isPresent()) {
						Grupo grupo = possivelGrupo.get();
						return new UsuarioGrupo(modelo, grupo);
					}
					return null;
				})
				.filter(grupo -> grupo != null)
				.collect(Collectors.toList());
		
		grupoUsuarioRepository.saveAll(listaGrupoUsuario);
		
		return usuario;
	} 
	
	public void deleteUsuarioById(Long id){
		usuarioRepository.deleteById(id);
	}
	
	public Usuario obterUsuarioComPermissoes(String login) {
		Optional<Usuario> usuarioEncontrado = usuarioRepository.findByLogin(login);
		if(usuarioEncontrado.isEmpty()) {
			return null;
		}
		
		Usuario usuario = usuarioEncontrado.get();
		List<String> permissoes = grupoUsuarioRepository.findPermissoesByUsuario(usuario);
		usuario.setPermissoes(permissoes);
		
		return usuario;
	}

}
