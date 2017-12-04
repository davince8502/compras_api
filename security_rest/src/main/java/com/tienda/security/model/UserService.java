package com.tienda.security.model;

/**
 * 
 * 
 *
 * Aug 17, 2016
 */
public interface UserService {
	
    public UserContext validateUserContext(String email, String password) throws Exception;
    
    
}
