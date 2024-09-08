package com.uol.compass.pb.ecommerce.domain.entities;

import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.ManyToAny;

import com.uol.compass.pb.ecommerce.enums.StatusVenda;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
	private List<Produto> produtos;

	private Double valorFinal;

	private Integer quantidade;

	private Double desconto;

	// private LocalDateTime horaDaVenda;

	private StatusVenda status;

	public Venda() {

	}

	public Venda(Usuario usuario, List<Produto> produtos, Double desconto) {
		this.usuario = usuario;
		//this.produtos = produtos;
		// Valor final será o preco diminuido do desconto da compra
		this.valorFinal = calcularTotal(produtos) - (calcularTotal(produtos) * desconto);
		this.desconto = desconto;
		this.status = StatusVenda.AGUARDANDO_PAGAMENTO;
	}
	
	public Double calcularTotal(List<Produto> produtos) {
		Double total = 0.0;
		for (Produto produto : produtos) {
			total += produto.getPreco();
		}
		return total;
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
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}
	/*
	public List<Produto> getProduto() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	*/
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
