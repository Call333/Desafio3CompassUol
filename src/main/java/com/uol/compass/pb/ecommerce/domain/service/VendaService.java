package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Venda;
import com.uol.compass.pb.ecommerce.domain.repository.VendaRepository;

@Service
public class VendaService {
	private final VendaRepository vendaRepository;

	public VendaService(VendaRepository vendaRepository) {
		super();
		this.vendaRepository = vendaRepository;
	}
	
	public Venda salvarVenda(Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public List<Venda> lerVendas() {
		return vendaRepository.findAll();
	}
	
	public Venda lerUmaVenaPorId(String id) {
		Optional<Venda> vendaEncontrada = vendaRepository.findById(id);
		Venda venda = vendaEncontrada.get();
		return venda;
	}
	
	public Venda atualizarVenda(String id, Venda venda) {
		Optional<Venda> vendaEncontrada = vendaRepository.findById(id);
		Venda vendaAtualizada = vendaEncontrada.get();
		
		vendaAtualizada.setUsuario(venda.getUsuario());
		vendaAtualizada.setProdutos(venda.getProdutos());
		vendaAtualizada.setValorFinal(venda.getValorFinal());
		vendaAtualizada.setDesconto(venda.getDesconto());
		vendaAtualizada.setStatus(venda.getStatus());
		
		return vendaRepository.save(vendaAtualizada);
	}
	
	public void deletarVenda(String id) {
		vendaRepository.deleteById(id);
	}
}
