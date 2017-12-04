package com.compras.commons.emuns;

/**
 * Enum que contiene los códigos de estado de las compras. 
 * 
 * @author 	MHernandez <br>
 * @date 04 de Dicimbre de 2017
 * @version 1.0
 */

public enum EstadoCompraEnum {
	
	INICIADO("INICIADO"),
	ENVIADO("ENVIADO"),    
	APROBADO("APROBADO"),
	RECHAZADO("RECHAZADO"),
	PAGADO("PAGADO"),
	CERRADO("CERRADO");
	
    private String code;

    private EstadoCompraEnum(String code) {
        this.code = code;
    }

   
    public String getCode() {
        return code;
    }

}
