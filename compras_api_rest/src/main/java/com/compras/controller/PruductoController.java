package com.compras.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compras.business.service.ProductService;
import com.compras.commons.contants.Constantes;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.dto.FieldErrorDTO;
import com.compras.domain.dto.ResponseServiceDTO;
import com.compras.domain.model.Producto;


/**
 * Controlador REST encargado de administrar las peticiones relacionadas a Productos.
 * 
 * @author 	MHernandez <br>
 * @date 03 de Diciembre de 2017
 * @version 1.0
 */

@RestController
public class PruductoController  extends AbstratcController{
	
	
	@Autowired
	private ProductService productService;
	
		
	
	@RequestMapping(value = Constantes.ENDPOINT_GET_PRODUCTO, method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> getProducto(@RequestParam("idProducto") Long idProducto) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {	
			
		
			try {
			
		 		Producto producto = productService.findById(idProducto);
				
				respuesta.setData(producto);
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
			
			
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constantes.ENDPOINT_SAVE_PRODUCTO, method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> saveProducto(@RequestBody Producto producto) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {
			
				validarEntidad(producto, respuesta);
			 		
				productService.saveProducto(producto);
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
				
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value = Constantes.ENDPOINT_DELETE_PRODUCTO, method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> deleteProducto(@RequestBody Producto producto) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {			
		
			productService.deleteProducto(producto.getId());
			respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
			
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constantes.ENDPOINT_UPDATE_PRODUCTO, method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> updateProducto(@RequestBody Producto producto) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {		
				
				if(producto.getId() != null &&  producto.getId() > 0){
					
					productService.updateProducto(producto);
					respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
					
				}else{
					
					List<FieldErrorDTO> errores = new ArrayList<>();					
					errores.add(new FieldErrorDTO("id", mesaggesGestorUtil.getMessage(Constantes.ERROR_VALIDACION_CAMPO_REQUERIDO, "id")));
					
					respuesta.setErrores(errores);
					throw new ValidationException(ErrorCodeEnum.CAMPOS_INVALIDOS);
					
				}
				
			}catch (ValidationException e) {
				return this.obtenerRespuestaFromValidateException(e, respuesta);
			}
			
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	

}
