package com.compras.domain.dto;

import java.util.List;

public class ResponseServiceDTO {

	private String responseCode;
	private String responseMessage;
	private Object data;	
	private List<FieldErrorDTO> errores;


	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<FieldErrorDTO> getErrores() {
		return errores;
	}

	public void setErrores(List<FieldErrorDTO> errores) {
		this.errores = errores;
	}

	

}
