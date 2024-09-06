package com.uol.compass.pb.ecommerce.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.pb.ecommerce.domain.entities.Venda;
import com.uol.compass.pb.ecommerce.domain.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	private final VendaService vendaService;

	public VendaController(VendaService vendaService) {
		super();
		this.vendaService = vendaService;
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda){
		Venda vendaCriada = vendaService.salvarVenda(venda);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaCriada);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<List<Venda>> LerVendas(){
		List<Venda> vendas = vendaService.lerVendas();
		return ResponseEntity.status(HttpStatus.OK).body(vendas);
	}
}
