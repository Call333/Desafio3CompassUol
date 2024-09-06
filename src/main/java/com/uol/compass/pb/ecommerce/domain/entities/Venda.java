package com.uol.compass.pb.ecommerce.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@OneToOne(mappedBy = "usuario")
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "produto")
	@JoinColumn(name = "id_produto")
	private List<Produto> produtos;
	
	private Double valorFinal;
	
	private Double desconto;
	
	private LocalDateTime horaDaVenda;
	
	private StatusVenda status;

	public Venda(Usuario usuario, List<Produto> produtos, Double valorFinal, Double desconto, LocalDateTime horaDaVenda,
			StatusVenda status) {
		super();
		this.usuario = usuario;
		this.produtos = produtos;
		this.valorFinal = valorFinal;
		this.desconto = desconto;
		this.horaDaVenda = horaDaVenda;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public LocalDateTime getHoraDaVenda() {
		return horaDaVenda;
	}

	public void setHoraDaVenda(LocalDateTime horaDaVenda) {
		this.horaDaVenda = horaDaVenda;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

	public List<Produto> getProdutos() {
		return produtos;
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
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
	
}	
