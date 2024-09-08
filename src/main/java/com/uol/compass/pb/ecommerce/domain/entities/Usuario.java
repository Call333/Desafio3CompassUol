package com.uol.compass.pb.ecommerce.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Min(value = 3)
	private String nome;
	
	@NotNull
	@NotEmpty
	@Min(value = 3)
	private String login;
	
	@NotEmpty
	@NotNull
	@Min(value = 3)
	private String senha;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Venda> pedidos;
	@JsonIgnore
	@Transient
	private List<String> permissoes;
	
	public Usuario() {
		
	}
	
	public Usuario( String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.pedidos = new ArrayList<>();
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Venda> getPedidos() {
		return pedidos;
	}
	
	public void setPedidos(List<Venda> pedidos) {
		this.pedidos = pedidos;
	}
	
	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(login, other.login) && Objects.equals(nome, other.nome);
	}
	
}
