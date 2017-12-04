package com.compras.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compras.business.service.TiendaService;
import com.compras.commons.contants.Constantes;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.dto.FieldErrorDTO;
import com.compras.domain.dto.ResponseServiceDTO;
import com.compras.domain.model.Producto;
import com.compras.domain.model.Tienda;


/**
 * Controlador REST encargado de administrar las peticiones relacionadas a Tiendas . 
 * 
 * @author 	MHernandez <br>
 * @date 04 de Diciembre de 2017
 * @version 1.0
 */

@RestController
public class TiendaController  extends AbstratcController{
	
	
	@Autowired
	private TiendaService tiendaService;
	
		
	
	@RequestMapping(value = Constantes.ENDPOINT_GET_TIENDA, method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> getTienda(@RequestParam("idTienda") Long idTienda) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {	
					 		
			Tienda tienda = tiendaService.findById(idTienda);
			
			respuesta.setData(tienda);
			respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constantes.ENDPOINT_SAVE_TIENDA, method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> saveProducto(@RequestBody Tienda tienda) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {
			
				validarEntidad(tienda, respuesta);
			 		
				tiendaService.saveTienda(tienda);
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
				
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value = Constantes.ENDPOINT_DELETE_TIENDA, method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> deleteProducto(@RequestBody Tienda tienda) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {			
		
			tiendaService.deleteTienda(tienda.getId());
			respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
			
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constantes.ENDPOINT_UPDATE_TIENDA, method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> updateProducto(@RequestBody Tienda tienda) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {		
				
				if(tienda.getId() != null &&  tienda.getId() > 0){
					
					tiendaService.updateTienda(tienda);
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
	
	
	
	
	@RequestMapping(value = Constantes.ENDPOINT_TIENDA_ADD_PRODUCTS, method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> addProductosToTienda(@RequestBody Tienda tienda) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {
				
				
				if(CollectionUtils.isNotEmpty(tienda.getProductos())){					
					
					for (Producto producto : tienda.getProductos()) {
						
						if(!(producto.getId() != null &&  producto.getId() > 0)){
					
							List<FieldErrorDTO> errores = new ArrayList<>();					
							errores.add(new FieldErrorDTO("id", mesaggesGestorUtil.getMessage(Constantes.ERROR_VALIDACION_CAMPO_REQUERIDO, "id")));
							
							respuesta.setErrores(errores);
							throw new ValidationException(ErrorCodeEnum.CAMPOS_INVALIDOS);							
						}
					}
					
				}else{
					throw new ValidationException(ErrorCodeEnum.PRODUCTS_LIST_EMPTY);
				}	
			
				
			 		
				tiendaService.addProductosToTienda(tienda);
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
				
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	

}
