package com.uol.compass.pb.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Future;

public class VendaDTO {
	
	private List<String> identificadorDosProdutos;
	private Integer quantidade;
	@Future
	@JsonIgnore
	private LocalDateTime horaDaVenda;
	
	public VendaDTO(List<String> identificadorDosProdutos, LocalDateTime horaDaVenda, Integer quantidade) {
		super();
		this.identificadorDosProdutos = identificadorDosProdutos;
		this.quantidade = quantidade;
		this.horaDaVenda = LocalDateTime.now();
	}
	public List<String> getIdentificadorDosProdutos() {
		return identificadorDosProdutos;
	}

	public void setIdentificadorDosProdutos(List<String> identificadorDosProdutos) {
		this.identificadorDosProdutos = identificadorDosProdutos;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public LocalDateTime getHoraDaVenda() {
		return horaDaVenda;
	}

	public void setHoraDaVenda(LocalDateTime horaDaVenda) {
		this.horaDaVenda = horaDaVenda;
	}
	
}
