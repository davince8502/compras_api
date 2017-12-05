package com.compras.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compras.business.service.CompraService;
import com.compras.commons.contants.Constantes;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.dto.ResponseServiceDTO;
import com.compras.domain.model.Compra;


/**
 * Controlador REST encargado de administrar las peticiones relacionadas a Compras . 
 * 
 * @author 	MHernandez <br>
 * @date 04 de Diciembre de 2017
 * @version 1.0
 */

@RestController
public class CompraController  extends AbstratcController{
	
	
	@Autowired
	private CompraService compraService;
	
		

	
	
	@RequestMapping(value = Constantes.ENDPOINT_SAVE_COMPRA, method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> saveCompra(@RequestBody Compra compra) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {
			
				validarEntidad(compra, respuesta);				
				
				if(CollectionUtils.isNotEmpty(compra.getProductos())){					
					
					this.validarCamposProductos(compra.getProductos(), respuesta);
					
				}else{
					throw new ValidationException(ErrorCodeEnum.PRODUCTS_LIST_EMPTY);
				}						
			 		
				compraService.saveCompra(compra);
				
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
