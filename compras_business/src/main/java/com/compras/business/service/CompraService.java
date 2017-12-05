package com.compras.business.service;

import com.compras.domain.model.Compra;

public interface CompraService {
	
	Compra findById(Long id);
	
	Compra saveCompra(Compra compra) throws Exception;

}
