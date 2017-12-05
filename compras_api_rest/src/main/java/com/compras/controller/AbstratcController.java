package com.compras.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.compras.commons.contants.Constantes;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.commons.util.MessagesGestorUtil;
import com.compras.domain.dto.FieldErrorDTO;
import com.compras.domain.dto.ResponseServiceDTO;
import com.compras.domain.model.Producto;
import com.compras.util.ValidationUtils;


public abstract class AbstratcController {

	
	private static Logger logger = Logger.getLogger(AbstratcController.class.getName());
	
	
	
	@Autowired
	protected MessagesGestorUtil mesaggesGestorUtil;
	
	@Autowired
	protected ValidationUtils validationUtils;
	
	
	/**
	 * Método para armar respuesta cuando se presenta errores de validacion o logica de negocio
	 * @param ResponseDTO : Objeto que contiene los campos que se van a validar 
	 * 
	 */
	protected ResponseEntity<ResponseServiceDTO> obtenerRespuestaFromValidateException(ValidationException e, ResponseServiceDTO respuesta){		
	
		
		if(e.getErrorCode() == null){
			respuesta.setResponseCode(ErrorCodeEnum.GENERAL_ERROR.getCode());
		}else{
			respuesta.setResponseCode(e.getErrorCode());
			
			String msg = (e.getMessage() == null) ? Constantes.PREF_COD_ERROR_GRAL.concat(e.getErrorCode()): e.getMessage();			
			
			respuesta.setResponseMessage(mesaggesGestorUtil.getMessage(msg, 
					ArrayUtils.isNotEmpty(e.getParams()) ? e.getParams(): null));
		}		
		
		logger.error(respuesta.getResponseMessage(), e);
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	
	/**
	 * Método para armar respuesta cuando se presenta un error inesperado
	 * @param e
	 * @param respuesta
	 */
	protected void obtenerRespuestaErrorGeneral(Exception e, ResponseServiceDTO respuesta){		
			
		
		respuesta.setResponseCode(ErrorCodeEnum.GENERAL_ERROR.getCode());
		respuesta.setResponseMessage(mesaggesGestorUtil.getMessage(Constantes.PREF_COD_ERROR_GRAL.concat(respuesta.getResponseCode())));
		
		logger.error(e.getMessage(), e);
	}
	
	
	
	protected void validarEntidad(Object obj, ResponseServiceDTO respuesta) throws ValidationException, IllegalAccessException {
		
		List<FieldErrorDTO> listaErrores = validationUtils.validate(obj, obj.getClass().getSimpleName());
		
		if(CollectionUtils.isNotEmpty(listaErrores)){
			
			for(FieldErrorDTO error: listaErrores){
				
				error.setError(mesaggesGestorUtil.getMessage(error.getError(), error.getField(),
						ArrayUtils.isNotEmpty(error.getParams()) ? error.getParams()[0] : null));
			}
			
			respuesta.setErrores(listaErrores);
			
			throw new ValidationException(ErrorCodeEnum.CAMPOS_INVALIDOS);				
			
		}
		
	}
	
	
	
	
	protected void validarCamposProductos(List<Producto> productos, ResponseServiceDTO respuesta) throws ValidationException {
		
		for (Producto producto : productos) {
			
			List<FieldErrorDTO> errores = new ArrayList<>();		
			
			if(!(producto.getId() != null &&  producto.getId() > 0)){							
				errores.add(new FieldErrorDTO("id", mesaggesGestorUtil.getMessage(Constantes.ERROR_VALIDACION_CAMPO_REQUERIDO, "id")));									
			}
			
			if(!(producto.getCantidad() != null &&  producto.getCantidad() > 0)){							
				errores.add(new FieldErrorDTO("cantidad", mesaggesGestorUtil.getMessage(Constantes.ERROR_VALIDACION_CAMPO_REQUERIDO, "cantidad")));									
			}
			
			if(!(producto.getPrecio() != null &&  producto.getPrecio() > 0)){							
				errores.add(new FieldErrorDTO("precio", mesaggesGestorUtil.getMessage(Constantes.ERROR_VALIDACION_CAMPO_REQUERIDO, "precio")));									
			}		
			
			if(CollectionUtils.isNotEmpty(errores)){
				respuesta.setErrores(errores);
				throw new ValidationException(ErrorCodeEnum.CAMPOS_INVALIDOS);	
			}			
			
		}
	}
	
	
	

}
