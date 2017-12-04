package com.tienda.security.auth.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model intended to be used for AJAX based authentication.
 * 
 * 
 *
 * Dic 29 2016
 */

public class LoginRequest {
    private String username;
    private String password;
    private Object details;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, 
    		@JsonProperty("password") String password,@JsonProperty("details") Object details) {
        this.username = username;
        this.password = password;
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

	public Object getDetails() {
		return details;
	}

}
