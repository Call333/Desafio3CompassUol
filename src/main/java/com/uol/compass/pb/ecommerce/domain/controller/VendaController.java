package com.uol.compass.pb.ecommerce.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.pb.ecommerce.domain.entities.Venda;
import com.uol.compass.pb.ecommerce.domain.security.CustomAuthentication;
import com.uol.compass.pb.ecommerce.domain.service.VendaService;

@RestController
// @RequestMapping("/vendas")
public class VendaController {
	private final VendaService vendaService;

	public VendaController(VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@PostMapping("/auth/vendas")
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Venda> criarVenda(CustomAuthentication authentication, @RequestBody Venda venda){
		Long id = authentication.getDetails().getId();
		Venda vendaCriada = vendaService.salvarVenda(id, venda);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaCriada);
	}
	
	@GetMapping("/vendas")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<List<Venda>> LerVendas(){
		List<Venda> vendas = vendaService.lerVendas();
		return ResponseEntity.status(HttpStatus.OK).body(vendas);
	}
	
	@GetMapping("/vendas/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Venda> LerVendasPorId(@PathVariable String id){
		Venda venda = vendaService.lerUmaVenaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(venda);
	}
	
	@GetMapping("/auth/vendas")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<List<Venda>> LerMinhasVendas(CustomAuthentication authentication){
		Long id = authentication.getDetails().getId();
		List<Venda> vendas = vendaService.lerVendasPorUsuario(id);
		return ResponseEntity.status(HttpStatus.OK).body(vendas);
	}
	
	@DeleteMapping("/usuario/{id_usuario}/vendas/{id_venda}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> DeletarVenda(@PathVariable Long id_usuario, @PathVariable String id_venda){
		vendaService.deletarVenda(id_usuario, id_venda);
		return ResponseEntity.status(HttpStatus.OK).body("Venda deletada");
	}
}
