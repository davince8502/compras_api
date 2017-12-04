package com.compras.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumerated roles.
 */
public enum TipoDocumento {
    TARJETA_IDENTIDAD("1"),CEDULA("2"), CEDULA_EXTRAJERIA("3");
	
	  
    private String codigo;

    private TipoDocumento(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }
    
   
}
