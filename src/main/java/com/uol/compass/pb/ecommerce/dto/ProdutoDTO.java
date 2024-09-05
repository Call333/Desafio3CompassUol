package com.uol.compass.pb.ecommerce.dto;

import java.util.Objects;

import com.uol.compass.pb.ecommerce.domain.entities.Produto;

public class ProdutoDTO {
	private String id;
	private Produto produto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		ProdutoDTO other = (ProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}

}
