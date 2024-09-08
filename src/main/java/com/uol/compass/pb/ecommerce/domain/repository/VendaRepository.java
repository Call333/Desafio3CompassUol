package com.uol.compass.pb.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uol.compass.pb.ecommerce.domain.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, String> {
}
