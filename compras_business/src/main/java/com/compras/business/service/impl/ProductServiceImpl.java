package com.compras.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.business.repository.ProductoRepository;
import com.compras.business.repository.TiendaProductoRepository;
import com.compras.business.service.ProductService;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.model.Producto;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TiendaProductoRepository tiendaProductoRepository;
	
	

	@Override
	public Producto findById(Long id) throws Exception{
		
		Producto producto =  productoRepository.findOne(id);
		
		if(producto == null){
			throw new ValidationException(ErrorCodeEnum.PRODUCTO_NO_FOUND, id);
		}
		
		return producto;
	}

	@Override
	public void saveProducto(Producto producto) throws Exception {
		
		Producto oldProducto =  productoRepository.findProductoByCodigoBarras(producto.getCodigoBarras());
		
		if(oldProducto != null){
			throw new ValidationException(ErrorCodeEnum.PRODUCTO_COD_REPETIDO);
		}
		
		
		this.productoRepository.save(producto);
	}
	
	
	@Override
	public void updateProducto(Producto producto) throws Exception {
		
		Producto oldProducto =  findById(producto.getId());
		
		if(oldProducto == null){
			throw new ValidationException(ErrorCodeEnum.PRODUCTO_NO_FOUND, producto.getId());
		}
		
		
		this.productoRepository.save(producto);
	}
	
	
	@Override
	public void deleteProducto(Long id) throws Exception {		
		
		Producto oldProducto =  findById(id);
		
		if(oldProducto == null){
			throw new ValidationException(ErrorCodeEnum.PRODUCTO_NO_FOUND, id);
		}
		
		
		if(tiendaProductoRepository.existProductoIntoSomeTienda(id) > 0){
			throw new ValidationException(ErrorCodeEnum.PRODUCTO_EN_TIENDA);
		}
		
		productoRepository.delete(id);	
		
	}
	
	

}
