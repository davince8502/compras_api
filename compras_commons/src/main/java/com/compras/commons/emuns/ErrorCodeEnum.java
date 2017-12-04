package com.compras.commons.emuns;

/**
 * Enum que contiene los códigos de error manejados en las validaciones. 
 * 
 * @author 	MHernandez <br>
 * @date 03 de Dicimbre de 2017
 * @version 1.0
 */

public enum ErrorCodeEnum {
	
	OK("000000"),
    GENERAL_ERROR("ERR000"),
    
    USER_NO_FOUND("AUTH01"),
	BAD_PASSWORD("AUTH02"),
	CLAVE_NUEVA_IGUAL("AUTH03"),
	SIN_ROLES("AUTH04"),
	EMAIL_REPETIDO("AUTH05"),
	
	PRODUCTO_COD_REPETIDO("ERR001"),
	
	PRODUCTO_EN_TIENDA("ERR002"),
	PRODUCTO_NO_FOUND("ERR003"),
	TIENDA_NO_FOUND("ERR004"),
	USER_IS_ADMIN("ERR005"),
	PRODUCTS_LIST_EMPTY("ERR006"),
	CLIENTE_NO_FOUND("ERR007"),
	PRODUCTO_NO_ESTA_EN_TIENDA("ERR008"),
	PRODUCTO_YA_ESTA_EN_TIENDA("ERR009"),
	
    
    
    CAMPOS_INVALIDOS("DATA00");
	
    private String code;

    private ErrorCodeEnum(String code) {
        this.code = code;
    }

   
    public String getCode() {
        return code;
    }

}
