package com.uol.compass.pb.ecommerce.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.uol.compass.pb.ecommerce.domain.security.CustomAuthentication;
import com.uol.compass.pb.ecommerce.domain.security.IdentificacaoUsuario;


@Component
public class SenhaMasterAuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		var userName = authentication.getName();
		var userCredentials = authentication.getCredentials();
		
		String loginMaster = "master";
		String passMaster = "@123";
		
		if(userName.equals(loginMaster) && userCredentials.equals(passMaster)) {
			/*
			return new UsernamePasswordAuthenticationToken
					("Usuario Master", null, List.of(new SimpleGrantedAuthority("ADMIN")));
			*/
			
			 IdentificacaoUsuario identificacaoUsuario = 
					new IdentificacaoUsuario(
							1001L, //Usuario Master", 
							"Master", 
							loginMaster, 
							List.of("ADMIN"));
			
			 return new CustomAuthentication(identificacaoUsuario);
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
