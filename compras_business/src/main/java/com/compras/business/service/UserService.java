package com.compras.business.service;

import com.compras.domain.model.Usuario;

/**
 * 
 * UserService
 */
public interface UserService extends com.tienda.security.model.UserService {
	
	
	Usuario getUserByEmail(String email) throws Exception;
	
	void crearUsuario(Usuario usuario)throws Exception;
	
	void deleteUsuario(Long id) throws Exception;

	void updateUsuario(Usuario usuario) throws Exception;
	
}
