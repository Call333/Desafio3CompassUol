package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Grupo;
import com.uol.compass.pb.ecommerce.domain.repository.GrupoRepository;

@Service
public class GrupoService {
	
	public final GrupoRepository grupoRepository;
	
	public GrupoService(GrupoRepository grupoRepository) {
		this.grupoRepository = grupoRepository;
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
		grupoRepository.deleteById(id);
	}
	
	public Grupo updateGrupo(Long id, Grupo grupo) {
		Grupo grupoEncontrado = searchGrupoById(id);
		grupoEncontrado.setNome(grupo.getNome());
		return grupoRepository.save(grupoEncontrado);
	}
	
}
