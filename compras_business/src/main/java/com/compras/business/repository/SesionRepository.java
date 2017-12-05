package com.compras.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.domain.model.Sesion;


/**
 * SessionRepository
 */
public interface SesionRepository extends JpaRepository<Sesion, Long> {
    
    
}
