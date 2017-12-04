package com.compras.commons.exception;

import com.compras.commons.emuns.ErrorCodeEnum;

public class ValidationException extends Exception {
	
private static final long serialVersionUID = -2724000364007460290L;
	
	//Atributos
	private Object [] params;
	private ErrorCodeEnum errorCodeEnum = null;
	private String errorCode = null;

	/**
	 * Constructor de ValidationException 
	 */
	public ValidationException() {
		super();
	}

	/**
	 * Constructor de ValidationException
	 * @param String s: Mensaje de Excepción
	 */
	public ValidationException(String s) {
		super(s);
	}
	
	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param Object [] parametros
	 */
	
	public ValidationException(String s, Object... params) {
		super(s);
		if(params != null){
			this.params = params;
		}
	}
	
	/**
	 * Constructor de ValidationException
	 * @param ErrorCodeRestEnum error 
	 * @param Object [] parametros
	 */
	public ValidationException(ErrorCodeEnum error, Object... params ) {
		
		this.errorCodeEnum = error;
		this.errorCode = error.getCode();
		if(params != null){
			this.params = params;
		}
	}

	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param Throwable rootCause : Causa de Excepción
	 */
	public ValidationException(String s, Throwable rootCause, Object... params) {
		super(s,rootCause);
		if(params != null){
			this.params = params;
		}
	}
	
	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param Throwable rootCause : Causa de Excepción
	 */
	public ValidationException(String s, Throwable rootCause) {
		super(s,rootCause);
	}

	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param String errorCode : Código de Error
	 */
	public ValidationException(String s, String errorCode) {
		super(s);
		this.errorCode = errorCode;
	}
	
	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param String errorCode : Código de Error
	 * @param Object [] parametros
	 */
	public ValidationException(String s, String errorCode, Object... params ) {
		this(s, params);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param String errorCode : Código de Error
	 * @param Throwable rootCause : Causa de Excepción
	 */
	public ValidationException(String s, String errorCode, Throwable cause) {
		this(s, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * Constructor de ValidationException
	 * @param String s : Mensaje de Excepción
	 * @param String errorCode : Código de Error
	 * @param Throwable rootCause : Causa de Excepción
	 * @param Object [] parametros
	 */
	public ValidationException(String s, String errorCode, Throwable cause, Object... params) {
		this(s, cause, params);
		this.errorCode = errorCode;
	}

	/**
	 * MÃ©todo para obtener Causa de Excepción
	 * @return java.lang.String Código de Error de Excepción
	 */	
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * 
	 * @return Object [] : Parametros opcionales para el mensaje
	 */
	public Object [] getParams() {
		return params;
	}

	/**
	 * @return {@link ErrorCodeRestEnum}
	 */	
	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

}
