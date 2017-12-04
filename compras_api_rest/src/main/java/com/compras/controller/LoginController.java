package com.compras.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compras.business.service.UserService;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.dto.ResponseServiceDTO;
import com.compras.domain.model.Usuario;
import com.tienda.security.config.WebSecurityConfig;
import com.tienda.security.model.UserContext;
import com.tienda.security.model.token.AccessJwtToken;
import com.tienda.security.model.token.JwtTokenFactory;


/**
 * Controlador REST encargado de administrar las peticiones de acceso al sistema al sistema . 
 * 
 * @author 	MHernandez <br>
 * @date 03 de Diciembre de 2017
 * @version 1.0
 */

@RestController
public class LoginController  extends AbstratcController{
	
	
	@Autowired
	private JwtTokenFactory tokenFactory;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = WebSecurityConfig.FORM_BASED_LOGIN_ENTRY_POINT, method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseServiceDTO> loginUser(@RequestBody Usuario usuario) {
		
		ResponseServiceDTO respuesta = new ResponseServiceDTO();
		
		try {
		 	try {
	
				UserContext userContext = userService.validateUserContext(usuario.getEmail(), usuario.getPassword());
	
				AccessJwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
				
				Usuario user = userService.getUserByEmail(usuario.getEmail());
				
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("accessToken", accessToken);
				resultMap.put("user", user);
	
				respuesta.setData(resultMap);
				respuesta.setResponseCode(ErrorCodeEnum.OK.getCode());
		    	
			}catch (ValidationException e) {
				return obtenerRespuestaFromValidateException(e, respuesta);
			}
	 	
		}catch (Exception e) {			
			respuesta.setResponseCode(ErrorCodeEnum.GENERAL_ERROR.getCode());
			respuesta.setResponseMessage(e.getMessage());
		}	
	 	return new ResponseEntity<ResponseServiceDTO>(respuesta, HttpStatus.OK);
    }
	
	

}
