package com.uol.compass.pb.ecommerce.domain.entities;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
	private Long id;
	private String nome;
	private String sobrenome;
	private String CPF;
	private String endereco;
	private LocalDate data_nascimento;

	private List<Produto> carrinho_de_compras;
	
	
}
