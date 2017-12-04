package com.compras.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumerated Estados de registros.
 */

public enum Estado {
	PENDIENTE("0"),ACTIVO("1"),BLOQUEADO("2"), CAMBIO_OBLIGATORIO_CLAVE("3");
	
	  
    private String codigo;

    private Estado(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }
    
   
}
