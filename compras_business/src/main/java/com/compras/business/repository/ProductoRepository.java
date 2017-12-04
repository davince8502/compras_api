package com.compras.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compras.domain.model.Producto;


/**
 * ProductoRepository
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	@Query("select p from Producto p where p.codigoBarras=:codigoBarra")
    public Producto findProductoByCodigoBarras(@Param("codigoBarra") String codigoBarra);

    
}
