package com.uol.compass.pb.ecommerce.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.pb.ecommerce.domain.entities.Produto;
import com.uol.compass.pb.ecommerce.domain.service.ProdutoService;
import com.uol.compass.pb.ecommerce.dto.ProdutoDTO;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		super();
		this.produtoService = produtoService;
	}
	
	// Metodo para criar um produto
	
	@PostMapping
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
		Produto produtoCriado = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
	}
	
	// Metodos para achar 1 produto
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<List<Produto>> acharProdutos() {
		List<Produto> produtos = produtoService.encontrarTodosOsProdutos();
		return ResponseEntity.status(HttpStatus.FOUND).body(produtos); 
	}
	
	@GetMapping(path = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Produto> acharProdutoPorId(@PathVariable String id){
		Produto produto = produtoService.encontrarProdutosPeloId(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(produto); 
	}
	
	// Metodo para atualizar um produto
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Produto> atualizarProduto(@RequestBody ProdutoDTO produtoDto){
		Produto produto = produtoService.atualizarProduto(produtoDto.getId(), produtoDto.getProduto());
		return ResponseEntity.status(HttpStatus.OK).body(produto); 
	}
	
	// Metodo para deletar o produto
	
	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletarProduto(@PathVariable String id){
		produtoService.deletarProduto(id);
		return ResponseEntity.status(HttpStatus.OK).body("Produto com id: " + id + " deletado!"); 
	}
}
