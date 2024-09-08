package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Produto;
import com.uol.compass.pb.ecommerce.domain.entities.Venda;
import com.uol.compass.pb.ecommerce.domain.repository.ProdutoRepository;

@Service
public  class ProdutoService {
	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	
	// Salvar produtos
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	// Achar produtos
	public List<Produto> encontrarTodosOsProdutos(){
		return produtoRepository.findAll();
	}
	
	public List<Produto> encontrarProdutosPeloNome(String nome) {
		List<Produto> produto = produtoRepository.findByNome(nome).get();
		return produto;
	}
	
	public Produto encontrarProdutosPeloId(String id) {
		Produto produto = produtoRepository.findById(id).get();
		return produto;
	}
	
	// Atualizar produtos
	public Produto atualizarProduto(String id, Produto produto) {
		Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
		Produto produtoAtualizado = produtoEncontrado.get();
		
		produtoAtualizado.setNome(produto.getNome());
		produtoAtualizado.setDescricao(produto.getDescricao());
		produtoAtualizado.setPreco(produto.getPreco());
		produtoAtualizado.setQuantidade(produto.getQuantidade());
		produtoAtualizado.setVendedor(produto.getVendedor());
		
		produtoRepository.save(produtoAtualizado);
		
		return produtoAtualizado;
	}
	
	// Deletar produtos
	public void deletarProduto(String id) {
		produtoRepository.deleteById(id);
	}

}
