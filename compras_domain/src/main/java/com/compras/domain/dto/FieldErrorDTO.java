package com.compras.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FieldErrorDTO {

	private String field;
	private String error;

	@JsonIgnore
	private Object[] params;

	public FieldErrorDTO() {
		super();
	}

	/**
	 * @param field
	 * @param error
	 */
	public FieldErrorDTO(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	/**
	 * @param field
	 * @param error
	 * @param params
	 */
	public FieldErrorDTO(String field, String error, Object... params) {
		super();
		this.field = field;
		this.error = error;
		this.params = params;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
