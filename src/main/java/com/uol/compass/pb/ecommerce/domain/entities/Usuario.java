package com.uol.compass.pb.ecommerce.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private DateTimeFormatter formmatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String sobrenome;
	private String cpf;
	private String endereco;
	
	@Temporal(TemporalType.DATE)
	private LocalDate data_nascimento;
	
	/*
	@JsonIgnore
	private List<Produto> carrinho_de_compras;
	*/
	public Usuario() {
		
	}
	
	public Usuario(String nome, String sobrenome, String cpf, String endereco, String data_nascimento) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.data_nascimento = LocalDate.parse(data_nascimento, formmatter);
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	/*
	public List<Produto> getCarrinho_de_compras() {
		return carrinho_de_compras;
	}
	*/
}
