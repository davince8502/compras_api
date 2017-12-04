package com.compras;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@ComponentScan(basePackages={"com.compras","com.tienda"})
@EnableConfigurationProperties
@SpringBootApplication
@EnableJpaAuditing
public class ComprasApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComprasApiRestApplication.class, args);
	}
	
	
	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
}
