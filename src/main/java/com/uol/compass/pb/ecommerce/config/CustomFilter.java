package com.uol.compass.pb.ecommerce.config;

import java.io.IOException;
import java.util.List;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.uol.compass.pb.ecommerce.domain.security.CustomAuthentication;
import com.uol.compass.pb.ecommerce.domain.security.IdentificacaoUsuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {

		String secretHeader = request.getHeader("x-secret");
		
		if (secretHeader != null) {
			if (secretHeader.equals("secr3t")) {
				var identificacaoUsuario = new IdentificacaoUsuario(
						1000L, // id-secret
						"muito secreto",
						"x-secret",
						List.of("ADMIN")
						);
				/*
				Authentication authentication = new UsernamePasswordAuthenticationToken
						("Usuario Muito Secreto", null, List.of(new SimpleGrantedAuthority("MASTER")));
				*/ 
				
				Authentication authentication = new CustomAuthentication(identificacaoUsuario);
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
			}
		}
		
		/*
		 * Necessário, pois sua ausência causa uma quebra na cadeia de filtros, 
		 * ele é responsável por chamar o próximo filtro
		 * */
	
		filterChain.doFilter(request, response);
	}
	
}
