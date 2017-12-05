package com.compras.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.business.repository.CompraProductoRepository;
import com.compras.business.repository.CompraRepository;
import com.compras.business.repository.ProductoRepository;
import com.compras.business.repository.TiendaProductoRepository;
import com.compras.business.repository.TiendaRepository;
import com.compras.business.repository.UserRepository;
import com.compras.business.service.CompraService;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.emuns.EstadoCompraEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.model.Compra;
import com.compras.domain.model.CompraProducto;
import com.compras.domain.model.Producto;
import com.compras.domain.model.Tienda;
import com.compras.domain.model.Usuario;



@Service
public class CompraServiceImpl implements CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private CompraProductoRepository compraProductoRepository;
	
	@Autowired 
	private TiendaProductoRepository tiendaProductoRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TiendaRepository tiendaRepository;
	

	@Override
	public Compra findById(Long id) {
		return compraRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Compra saveCompra(Compra compra) throws Exception {
		
		
		Usuario user = userRepository.findOne(compra.getIdCliente());
		
		if(user == null){
			throw new ValidationException(ErrorCodeEnum.CLIENTE_NO_FOUND, compra.getIdCliente());
		}
		
		Tienda tienda = tiendaRepository.findOne(compra.getIdTienda());
		
		if(tienda == null){
			throw new ValidationException(ErrorCodeEnum.TIENDA_NO_FOUND, compra.getIdTienda());
		}
		
		
		compra.setEstado(EstadoCompraEnum.INICIADO.getCode());
		compra.setCliente(user);
		compra.setTienda(tienda);
		
		List<Producto> productosTienda = new ArrayList<>();
				
		compraRepository.save(compra);
		
		List<Producto> endProductos = new ArrayList<>();
		
		if(CollectionUtils.isNotEmpty(compra.getProductos())){
			
			
			for (Producto producto : compra.getProductos()) {
				
				Producto OldProducto = productoRepository.findOne(producto.getId());
				
				if(OldProducto == null){
					throw new ValidationException(ErrorCodeEnum.PRODUCTO_NO_FOUND, producto.getId());
				}				
				
				if(tiendaProductoRepository.existProductoIntoTienda(producto.getId(), compra.getIdTienda() ) == 0){
					throw new ValidationException(ErrorCodeEnum.PRODUCTO_NO_ESTA_EN_TIENDA, producto.getId(), compra.getIdTienda());
				}
				
				endProductos.add(OldProducto);
				productosTienda.add(OldProducto);
				
				CompraProducto compraProducto = new CompraProducto();
				compraProducto.setCompra(compra);
				compraProducto.setProducto(producto);
				compraProducto.setPrecio(producto.getPrecio());
				compraProducto.setCantidad(producto.getCantidad());
				
				compraProductoRepository.save(compraProducto);
				
			}		
			
		}
		
		tienda.setProductos(productosTienda);
		compra.setProductos(endProductos);
		
		return compra;
		
		
	}

}
