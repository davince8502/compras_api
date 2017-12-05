package com.compras.business.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.business.repository.ProductoRepository;
import com.compras.business.repository.TiendaProductoRepository;
import com.compras.business.repository.TiendaRepository;
import com.compras.business.service.TiendaService;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.model.Producto;
import com.compras.domain.model.Tienda;
import com.compras.domain.model.TiendaProducto;


@Service
public class TiendaServiceImpl implements TiendaService {
	
	@Autowired
	private TiendaRepository tiendaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TiendaProductoRepository tiendaProductoRepository;
	
	

	@Override
	public Tienda findById(Long id) {
		return this.tiendaRepository.findOne(id);
	}

	@Override
	public void saveTienda(Tienda tienda) {		
		this.tiendaRepository.save(tienda);
	}

	@Override
	public void deleteTienda(Long id) throws Exception {
		
		Tienda oldTienda =  findById(id);
		
		if(oldTienda == null){
			throw new ValidationException(ErrorCodeEnum.TIENDA_NO_FOUND, id);
		}		
		
		this.tiendaRepository.delete(id);
	}

	@Override
	public void updateTienda(Tienda tienda) throws Exception {
		
		Tienda oldTienda =  findById(tienda.getId());
		
		if(oldTienda == null){
			throw new ValidationException(ErrorCodeEnum.TIENDA_NO_FOUND, tienda.getId());
		}		
		
		this.tiendaRepository.save(tienda);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void addProductosToTienda(Tienda tienda) throws Exception  {
		
		Tienda tiendaOld = tiendaRepository.findOne(tienda.getId());
		
		if(tiendaOld == null){
			throw new ValidationException(ErrorCodeEnum.TIENDA_NO_FOUND, tienda.getId());
		}
		
		
		if(CollectionUtils.isNotEmpty(tienda.getProductos())){
		
		
			for (Producto producto : tienda.getProductos()) {
				
				Producto OldProducto = productoRepository.findOne(producto.getId());
				
				if(OldProducto == null){
					throw new ValidationException(ErrorCodeEnum.PRODUCTO_NO_FOUND, producto.getId());
				}
				
				if(tiendaProductoRepository.existProductoIntoTienda(producto.getId(), tienda.getId()) > 0){					
					throw new ValidationException(ErrorCodeEnum.PRODUCTO_YA_ESTA_EN_TIENDA, producto.getId(), tienda.getId() );					
				}
				
				
				TiendaProducto tiendaProducto = new TiendaProducto();
				tiendaProducto.setTienda(tiendaOld);
				tiendaProducto.setProducto(producto);
				tiendaProducto.setCantidad(producto.getCantidad());
				tiendaProducto.setPrecio(producto.getPrecio());
				
				tiendaProductoRepository.save(tiendaProducto);
			}		
			
		}
		
		
	}
	
	

}
