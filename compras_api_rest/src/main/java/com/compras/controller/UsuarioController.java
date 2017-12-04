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

import com.compras.business.service.UserService;
import com.compras.commons.contants.Constantes;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.dto.FieldErrorDTO;
import com.compras.domain.dto.ResponseServiceDTO;
import com.compras.domain.model.Usuario;
import com.compras.domain.model.UsuarioRole;


/**
 * Controlador REST encargado de administrar las peticiones relacionadas a Tiendas . 
 * 
 * @author 	MHernandez <br>
 * @date 04 de Diciembre de 2017
 * @version 1.0
 */

@RestController
public class UsuarioController  extends AbstratcController{
	
	
	@Autowired
	private UserService UsuarioService;
	
		
	
	@RequestMapping(value = Constantes.ENDPOINT_GET_USUARIO, method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> getUsuario(@RequestParam("email") String email) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {	
					 		
			Usuario usuario = UsuarioService.getUserByEmail(email);
			
			respuesta.setData(usuario);
			respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constantes.ENDPOINT_SAVE_USUARIO, method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> saveUsuario(@RequestBody Usuario usuario) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {
			
				validarEntidad(usuario, respuesta);
				
				if(CollectionUtils.isNotEmpty(usuario.getUsuarioRoles())){		
					
					for (UsuarioRole userRole : usuario.getUsuarioRoles()) {						
						validarEntidad(userRole, respuesta);						
					}
					
				}				
			 		
				UsuarioService.crearUsuario(usuario);
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
				
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value = Constantes.ENDPOINT_DELETE_USUARIO, method=RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> deleteUsuario(@RequestBody Usuario usuario) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {
			
			try {
		
				UsuarioService.deleteUsuario(usuario.getId());
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
				
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
			
	 	
		}catch (Exception e) {			
			this.obtenerRespuestaErrorGeneral(e, respuesta);
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constantes.ENDPOINT_UPDATE_USUARIO, method=RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> updateUsuario(@RequestBody Usuario usuario) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {				
		
			try {		
				
				if(usuario.getId() != null &&  usuario.getId() > 0){
					
					UsuarioService.updateUsuario(usuario);
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
