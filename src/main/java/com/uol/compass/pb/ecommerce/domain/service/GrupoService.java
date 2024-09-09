package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Grupo;
import com.uol.compass.pb.ecommerce.domain.repository.GrupoRepository;
import com.uol.compass.pb.ecommerce.domain.repository.UsuarioGrupoRepository;

@Service
public class GrupoService {
	
	public final GrupoRepository grupoRepository;
	private final UsuarioGrupoRepository usuarioGrupoRepository;
	
	public GrupoService(GrupoRepository grupoRepository,
			UsuarioGrupoRepository usuarioGrupoRepository) {
		this.grupoRepository = grupoRepository;
		this.usuarioGrupoRepository = usuarioGrupoRepository;
	}
	
	public Grupo saveGrupo(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	public Grupo searchGrupoById(Long id) {
		return grupoRepository.findById(id).get();
	}
	
	public List<Grupo> searchAllGrupo() {
		return grupoRepository.findAll();
	}
	
	public void deleteGrupo(Long id) {
		usuarioGrupoRepository.deletePermissoesById(id);
		grupoRepository.deleteById(id);
	}
	
	public Grupo updateGrupo(Long id, Grupo grupo) {
		Grupo grupoEncontrado = searchGrupoById(id);
		grupoEncontrado.setNome(grupo.getNome());
		return grupoRepository.save(grupoEncontrado);
	}
	
}