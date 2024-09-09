package com.uol.compass.pb.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.pb.ecommerce.domain.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, String> {
}
