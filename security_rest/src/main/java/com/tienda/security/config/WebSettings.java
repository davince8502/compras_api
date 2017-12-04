package com.tienda.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest.security.web")
public class WebSettings {
    /**
     * {@link WebSettings} Path sin restriccion de acceso por autenticacion.
     */
    private String pathWihtoutAuth;
    /**
     * {@link WebSettings} Path con restriccion de acceso por autenticacion con token valido.
     */
    private String tokenBasedAuthEntryPoint;

	public String getPathWihtoutAuth() {
		return pathWihtoutAuth;
	}

	public void setPathWihtoutAuth(String pathWihtoutAuth) {
		this.pathWihtoutAuth = pathWihtoutAuth;
	}

	public String getTokenBasedAuthEntryPoint() {
		return tokenBasedAuthEntryPoint;
	}

	public void setTokenBasedAuthEntryPoint(String tokenBasedAuthEntryPoint) {
		this.tokenBasedAuthEntryPoint = tokenBasedAuthEntryPoint;
	}

	

   
}
