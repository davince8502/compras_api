package com.compras.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compras.domain.model.Compra;


/**
 * CompraRepository
 */
public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	@Query("select c from Compra c where c.cliente.id = :idCliente")
    public List<Compra> findComprasOfUsuario(@Param("idCliente") Long idCliente);

    
}
