package com.compras.commons.contants;

public final class Constantes {
	
	
	/*CONSTANTES GENERALES*/
	
	public static final String STRING_EMPTY ="";	

	public static final String ROL_ADMIN_NAME ="Administrador";	
	
	
	/*CONSTANTES PARA MENSAJES GENERALES*/
	
	public static final String PREF_COD_ERROR_GRAL= "error.message.gral.";
	
	public static final String ERROR_GENERAL ="error.general";	
	
	
	/* Expresiones Regulares para Validaciones */	
	public static final String REGEX_NUM_POSITIVE =  "^(0?|0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[0-9][0-9]*)$";
	
	
	
	/*VALIDACIONES CAMPOS DTOS y OBJETOS */
	
	public static final String ERROR_VALIDACION_CAMPO_NULO ="validacion.obj.field.null";
	public static final String ERROR_VALIDACION_CAMPO_REQUERIDO ="validacion.obj.field.requerid";
	public static final String ERROR_VALIDACION_CAMPO_MAX_TAMANO ="validacion.obj.field.maxLength";
	public static final String ERROR_VALIDACION_CAMPO_MIN_TAMANO ="validacion.obj.field.minLength";
	public static final String ERROR_VALIDACION_CAMPO_INT_POSITIVO ="validacion.obj.field.IntegerPositive";
	public static final String ERROR_VALIDACION_CAMPO_DOUBLE ="validacion.obj.field.Double";
	public static final String ERROR_VALIDACION_CAMPO_FECHA_FORMATO ="validacion.obj.field.Fecha.formato";
	public static final String ERROR_VALIDACION_CAMPO_EMAIL_FORMATO ="validacion.obj.field.Email.formato";
	
	
	
	public static final String PREF_ENDPOINT_WITHOUTAUTH = "/withoutAuth";
	
	public static final String PREF_ENDPOINT_AUTH= "/auth";
	
	/*ENDPOINTS TRANSACCIONES WS */
	
	public static final String ENDPOINT_GET_USUARIO = PREF_ENDPOINT_AUTH + "/getUsuario";
	public static final String ENDPOINT_SAVE_USUARIO = PREF_ENDPOINT_AUTH + "/saveUsuario";
	public static final String ENDPOINT_DELETE_USUARIO = PREF_ENDPOINT_AUTH + "/deleteUsuario";
	public static final String ENDPOINT_UPDATE_USUARIO = PREF_ENDPOINT_AUTH + "/updateUsuario";
	
	public static final String ENDPOINT_GET_PRODUCTO = PREF_ENDPOINT_AUTH + "/getProducto";
	public static final String ENDPOINT_SAVE_PRODUCTO = PREF_ENDPOINT_AUTH + "/saveProducto";
	public static final String ENDPOINT_DELETE_PRODUCTO = PREF_ENDPOINT_AUTH + "/deleteProducto";
	public static final String ENDPOINT_UPDATE_PRODUCTO = PREF_ENDPOINT_AUTH + "/updateProducto";
	
	public static final String ENDPOINT_GET_TIENDA = PREF_ENDPOINT_AUTH + "/getTienda";
	public static final String ENDPOINT_SAVE_TIENDA = PREF_ENDPOINT_AUTH + "/saveTienda";
	public static final String ENDPOINT_DELETE_TIENDA = PREF_ENDPOINT_AUTH + "/deleteTienda";
	public static final String ENDPOINT_UPDATE_TIENDA = PREF_ENDPOINT_AUTH + "/updateTienda";
	public static final String ENDPOINT_TIENDA_ADD_PRODUCTS = PREF_ENDPOINT_AUTH + "/addProductosToTienda";
	
	public static final String ENDPOINT_SAVE_COMPRA = PREF_ENDPOINT_AUTH + "/saveCompra";

}
