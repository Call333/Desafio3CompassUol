package com.uol.compass.pb.ecommerce.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.pb.ecommerce.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
	Optional<List<Produto>> findByVendedor(String nome);
}
