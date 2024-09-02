package com.uol.compass.pb.ecommerce.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.service.UsuarioService;
import com.uol.compass.pb.ecommerce.security.CustomAuthentication;
import com.uol.compass.pb.ecommerce.security.IdentificacaoUsuario;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private final UsuarioService usuarioService;
	private final PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String login = authentication.getName();
		String senha = (String) authentication.getCredentials();
		
		Usuario usuario = usuarioService.obterUsuarioComPermissoes(login);
		if(usuario != null) {
			boolean matches = passwordEncoder.matches(senha, usuario.getSenha());
			if(matches) {
				IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
						usuario.getId(),
						usuario.getNome(),
						usuario.getLogin(),
						usuario.getPermissoes());	
				
				return new CustomAuthentication(identificacaoUsuario);
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
