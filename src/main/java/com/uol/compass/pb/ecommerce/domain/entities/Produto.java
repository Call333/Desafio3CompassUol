package com.uol.compass.pb.ecommerce.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//@Entity
public class Produto {
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private Integer quantidades;
	private Integer qtd_vendidas;
	private Double avaliacoes;
	private Integer qtd_avaliacoes;
	
	//@JsonIgnore
	//private List<String> tipos;
	
	public Produto() {
		
	}
	
	public Produto(String nome, String descricao, Integer quantidades) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.quantidades = quantidades;
		this.qtd_vendidas = 0;
		this.avaliacoes = 0.0;
		this.qtd_avaliacoes = 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(Integer quantidades) {
		this.quantidades = quantidades;
	}

	public Integer getQtd_vendidas() {
		return qtd_vendidas;
	}

	public void setQtd_vendidas(Integer qtd_vendidas) {
		this.qtd_vendidas = qtd_vendidas;
	}

	public Double getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Double avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Integer getQtd_avaliacoes() {
		return qtd_avaliacoes;
	}

	public void setQtd_avaliacoes(Integer qtd_avaliacoes) {
		this.qtd_avaliacoes = qtd_avaliacoes;
	}
	
	/*
	public List<String> getTipos() {
		return tipos;
	} 
	*/
}
