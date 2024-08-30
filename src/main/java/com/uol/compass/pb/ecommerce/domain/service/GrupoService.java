package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Grupo;
import com.uol.compass.pb.ecommerce.domain.repository.GrupoRepository;

@Service
public class GrupoService {
	
	public final GrupoRepository grupoRepository;
	
	public GrupoService(GrupoRepository grupoRepository) {
		this.grupoRepository = grupoRepository;
	}
	
	public Grupo createGrupo(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	public Optional<Grupo> seachGrupoById(Long id) {
		return grupoRepository.findById(id);
	}
	
	public List<Grupo> seachAllGrupo() {
		return grupoRepository.findAll();
	}
	
	public void deleteGrupo(Long id) {
		grupoRepository.deleteById(id);
	}
	
	public Grupo updateGrupo(Long id, Grupo grupo) {
		Grupo grupoEncontrado = seachGrupoById(id).get();
		grupoEncontrado.setNome(grupo.getNome());
		return grupoRepository.save(grupoEncontrado);
	}
	
}
