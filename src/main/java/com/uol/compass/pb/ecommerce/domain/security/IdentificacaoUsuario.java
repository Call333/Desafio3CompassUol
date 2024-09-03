package com.uol.compass.pb.ecommerce.domain.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class IdentificacaoUsuario {
	private Long id;
	private String nome;
	private String login;
	private List<String> permissoes;
	
	public IdentificacaoUsuario() {
		
	}
	
	public IdentificacaoUsuario(Long id, String nome, String login, List<String> permissoes) {
	
		this.id = id;
		this.nome = nome;
		this.login = login;
		// Essa validacao estava no metodo getPermissoes()
		if (permissoes == null) {
			permissoes = new ArrayList<>();
		}
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

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}
	
	public List<String> getPermissoes() {
		return permissoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, nome, permissoes);
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
		return Objects.equals(id, other.id) && Objects.equals(login, other.login) && Objects.equals(nome, other.nome)
				&& Objects.equals(permissoes, other.permissoes);
	}
	
}
