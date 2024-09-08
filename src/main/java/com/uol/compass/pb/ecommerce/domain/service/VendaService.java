package com.uol.compass.pb.ecommerce.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uol.compass.pb.ecommerce.domain.entities.Usuario;
import com.uol.compass.pb.ecommerce.domain.entities.Venda;
import com.uol.compass.pb.ecommerce.domain.repository.UsuarioRepository;
import com.uol.compass.pb.ecommerce.domain.repository.VendaRepository;

@Service
public class VendaService {
	private final VendaRepository vendaRepository;
	private final UsuarioRepository usuarioRepository;

	public VendaService(VendaRepository vendaRepository,
			UsuarioRepository usuarioRepository) {
		this.vendaRepository = vendaRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public Venda salvarVenda(Long id, Venda venda) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		Usuario usuario = usuarioOptional.get();
		
		usuario.getPedidos().add(venda);
		Usuario save = usuarioRepository.save(usuario);
		
		venda.setUsuario(usuario);
		
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
	
	/*
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
	*/
	
	public void deletarVenda(Long id_usuario, String id_venda) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id_usuario);
		
		Usuario usuario = usuarioOptional.get();
		
		List<Venda> listaDeVendasDoUsuario = usuario.getPedidos();
		
		listaDeVendasDoUsuario.stream().map( id_venda_do_usuario -> {
			if(id_venda_do_usuario.getId().equals(id_venda)) {
				vendaRepository.deleteById(id_venda);
			}
			return id_venda_do_usuario;
		}).collect(Collectors.toList());
		
		usuario.setPedidos(listaDeVendasDoUsuario);
		
		usuarioRepository.save(usuario);
	}
	
	public List<Venda> lerVendasPorUsuario(Long id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		Usuario usuario = usuarioOptional.get();
		
		List<Venda> vendasPorUsuario = usuario.getPedidos();
		
		return vendasPorUsuario;
	}
}
