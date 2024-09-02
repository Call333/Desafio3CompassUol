package com.uol.compass.pb.ecommerce.dto;

import java.util.List;
import java.util.Objects;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;

public class CadastroUsuarioDTO {
	private Usuario usuario;
	private List<String> permissoes;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(permissoes, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroUsuarioDTO other = (CadastroUsuarioDTO) obj;
		return Objects.equals(permissoes, other.permissoes) && Objects.equals(usuario, other.usuario);
	}

}
