package com.compras.business.service;

import com.compras.domain.model.Tienda;

public interface TiendaService {
	
	Tienda findById(Long id);
	
	void saveTienda(Tienda tienda);

	void deleteTienda(Long id) throws Exception;

	void updateTienda(Tienda tienda) throws Exception;
	
	void addProductosToTienda(Tienda tienda) throws Exception ;

}
