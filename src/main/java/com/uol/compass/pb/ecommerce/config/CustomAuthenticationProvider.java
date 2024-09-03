package com.uol.compass.pb.ecommerce.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.security.CustomAuthentication;
import com.uol.compass.pb.ecommerce.domain.security.IdentificacaoUsuario;
import com.uol.compass.pb.ecommerce.domain.service.UsuarioService;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final UsuarioService usuarioSevice;
	private final PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(UsuarioService usuarioSevice, PasswordEncoder passwordEncoder) {
		super();
		this.usuarioSevice = usuarioSevice;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String login = authentication.getName();
		String senha = (String) authentication.getCredentials();
		
		Usuario usuario = usuarioSevice.obterUsuarioComPermissoes(login);
		
		if(usuario != null) {
			boolean senhasBatem = passwordEncoder.matches(senha, usuario.getSenha());
			if(senhasBatem) {
				 IdentificacaoUsuario identificacaoUsuario = 
						 new IdentificacaoUsuario(
								 usuario.getId(),
								 usuario.getNome(), 
								 usuario.getLogin(), 
								 usuario.getPermissoes()
								 );
				 
				 return new CustomAuthentication(identificacaoUsuario);
			}
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
