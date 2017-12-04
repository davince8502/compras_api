package com.compras.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compras.domain.model.TiendaProducto;


/**
 * UserRepository
 */
public interface TiendaProductoRepository extends JpaRepository<TiendaProducto, Long> {
	
	
	@Query(" select count(*) from TiendaProducto tp "
			+ "where tp.producto.id = :idProducto "
			+ "AND tp.tienda.id = :idTienda")
    public Long existProductoIntoTienda(@Param("idProducto") Long idProducto, @Param("idTienda") Long idTienda );
	
	@Query(" select count(*) from TiendaProducto tp "
			+ "where tp.producto.id = :idProducto ")
    public Long existProductoIntoSomeTienda(@Param("idProducto") Long idProducto);
    
    
}
