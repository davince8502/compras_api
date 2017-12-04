package com.tienda.security.auth.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.security.auth.exceptions.AuthMethodNotSupportedException;
import com.tienda.security.auth.exceptions.JwtExpiredTokenException;
import com.tienda.security.common.ErrorCode;
import com.tienda.security.common.ErrorResponse;

/**
 * 
 * 
 *
 * Dic 29 2016
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;
    
    @Autowired
    public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }	
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	
		
		if (e instanceof BadCredentialsException) {
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication failed: "+e.getMessage(), ErrorCode.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof JwtExpiredTokenException) {
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof AuthMethodNotSupportedException) {
		    mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.METHOD_NO_SUPPORTED, HttpStatus.METHOD_NOT_ALLOWED));
		}else if (e instanceof UsernameNotFoundException){
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication failed: "+e.getMessage(), ErrorCode.USER_NO_FOUND, HttpStatus.NOT_FOUND));
		}else{
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication failed: "+e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		}
	}
}
