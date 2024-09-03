package com.uol.compass.pb.ecommerce.domain.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthentication implements Authentication{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8817812024869450180L;
	
	private final IdentificacaoUsuario identificacaoUsuario;
	
	public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
		if(identificacaoUsuario == null) {
			throw new ExceptionInInitializerError("Não é possível criar um CustomAuthentication sem a identificação do usuario");
		}
		this.identificacaoUsuario = identificacaoUsuario;
	}
	
	@Override
	public String getName() {
		return this.identificacaoUsuario.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.identificacaoUsuario
				.getPermissoes()
				.stream()
				.map(perm -> new SimpleGrantedAuthority(perm))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.identificacaoUsuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new IllegalArgumentException("Usuário já autenticado.");
		
	}

}
