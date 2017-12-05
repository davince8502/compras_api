package com.compras.commons.emuns;

/**
 * Enum que contiene los códigos de Tipo de Pago de las compras. 
 * 
 * @author 	MHernandez <br>
 * @date 04 de Dicimbre de 2017
 * @version 1.0
 */

public enum TipoPagoEnum {
	
	DEBITO(1),
	CREDITO(2),
	CONTRA_ENTREGA(3);
	
    private Integer code;

    private TipoPagoEnum(Integer code) {
        this.code = code;
    }

   
    public Integer getCode() {
        return code;
    }

}
