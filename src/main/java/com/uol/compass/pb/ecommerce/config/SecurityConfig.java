package com.uol.compass.pb.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilter(
			HttpSecurity http, 
			SenhaMasterAuthProvider senhaMasterAuthProvider,
			CustomFilter customFilter,
			CustomAuthenticationProvider customAuthenticationProvider) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						customizer -> {
							customizer.anyRequest().authenticated();
							})
				/*
				 * Habilita a autenticação basic - "Basic Auth retornando um status http 401
				 * Se ela não for habilitada, todos páginas que não foram permitidas as requisições
				 * Retornaram um status 403 - Forbbiden*/
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.authenticationProvider(senhaMasterAuthProvider)
				.authenticationProvider(customAuthenticationProvider)
				.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
		
	@Bean
	public UserDetailsService userService() {
		
		UserDetails commonUser = User.builder()
				.username("user")
				.password(passwordEnconder().encode("1234"))
				.roles("USER")
				.build();
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password(passwordEnconder().encode("admin"))
				.roles("USER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(commonUser, adminUser);
	}
	
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}
	
}
