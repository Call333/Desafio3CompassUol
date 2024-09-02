package com.uol.compass.pb.ecommerce.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthentication implements Authentication{

	private static final long serialVersionUID = 1L;
	
	private final IdentificacaoUsuario identificacaoUsuario;
	
	public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
		if(identificacaoUsuario == null) {
			throw new ExceptionInInitializerError
			("Nao e possivel criar uma autenticacao customizada do usuario sem a identificacao do usuario");
		}
		this.identificacaoUsuario = identificacaoUsuario;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.identificacaoUsuario.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.identificacaoUsuario
				.getPermissoes()
				.stream()
				.map(perm -> new SimpleGrantedAuthority(perm))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.identificacaoUsuario;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException("Usuario autenticado");
	}

}
