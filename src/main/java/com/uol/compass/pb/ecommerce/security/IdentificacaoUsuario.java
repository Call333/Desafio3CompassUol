package com.uol.compass.pb.ecommerce.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IdentificacaoUsuario {
	private Long id;
	private String nome;
	private String login;
	private List<String> permissoes;

	public IdentificacaoUsuario() {
		
	}
	
	public IdentificacaoUsuario(Long id, String nome, String login, List<String> permissoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.permissoes = permissoes;
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

	public List<String> getPermissoes() {
		if(permissoes == null) {
			permissoes = new ArrayList<>();
		}
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentificacaoUsuario other = (IdentificacaoUsuario) obj;
		return Objects.equals(id, other.id);
	}

}
