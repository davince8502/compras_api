package com.tienda.security.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of REST Error types.
 * 
 * 
 *
 * Dic 29 2016
 */
public enum ErrorCode {
	AUTHENTICATION(1000), USER_NO_FOUND(1001), BAD_CREDENTIALS(1002), JWT_TOKEN_EXPIRED(1003), METHOD_NO_SUPPORTED(1004);
    
    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
