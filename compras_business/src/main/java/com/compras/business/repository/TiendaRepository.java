package com.compras.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.domain.model.Tienda;


/**
 * UserRepository
 */
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    
    
}
