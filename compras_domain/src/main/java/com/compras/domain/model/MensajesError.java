package com.compras.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mensajes_error database table.
 * 
 */
@Entity
@Table(name="mensajes_error")
@NamedQuery(name="MensajesError.findAll", query="SELECT m FROM MensajesError m")
public class MensajesError extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String codigo;

	private Boolean activo;

	private Boolean generico;

	@Column(name="id_compania")
	private Long idCompania;

	private String mensaje;

	public MensajesError() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getGenerico() {
		return this.generico;
	}

	public void setGenerico(Boolean generico) {
		this.generico = generico;
	}

	public Long getIdCompania() {
		return this.idCompania;
	}

	public void setIdCompania(Long idCompania) {
		this.idCompania = idCompania;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}