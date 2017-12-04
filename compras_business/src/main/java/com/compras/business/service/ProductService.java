package com.compras.business.service;

import com.compras.domain.model.Producto;

public interface ProductService {
	
	Producto findById(Long id);
	
	void saveProducto(Producto producto) throws Exception;

	void deleteProducto(Long id) throws Exception;

	void updateProducto(Producto producto) throws Exception;

}
