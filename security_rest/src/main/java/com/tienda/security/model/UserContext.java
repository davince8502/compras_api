package com.tienda.security.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * 
 *
 * Aug 4, 2016
 */
public class UserContext {
	
	private Object dataUser;
    private final String username;
    private final List<GrantedAuthority> authorities;

    private UserContext(String username, List<GrantedAuthority> authorities, Object dataUser) {
    	this.dataUser = dataUser;
        this.username = username;
        this.authorities = authorities;
    }
    
    
    public static UserContext create(String username, List<GrantedAuthority> authorities, Object dataUser) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank" );
        return new UserContext(username, authorities, dataUser);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

	public Object getDataUser() {
		return dataUser;
	}
}
