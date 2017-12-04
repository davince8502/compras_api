package com.compras.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value="classpath:properties/aplicacion.properties", ignoreResourceNotFound= true)
@Configuration
@ConfigurationProperties(prefix = "application")
public class AplicacionSettings {

	
		
}
