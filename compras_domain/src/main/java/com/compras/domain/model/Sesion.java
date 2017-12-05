package com.compras.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the sesion database table.
 * 
 */
@Entity
@NamedQuery(name="Sesion.findAll", query="SELECT s FROM Sesion s")
public class Sesion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="creado_en")
	private Date creadoEn;

	@Column(name="datos_sesion")
	private String datosSesion;


	@Column(name="ingreso_en")
	private Date ingresoEn;

	@Column(name="num_intentos")
	private Long numIntentos;

	@Column(name="sesion_navegador")
	private String sesionNavegador;

	private String token;


	@Column(name="id_usuario")
	private Long idUsuario;
	
	
	

	public Sesion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreadoEn() {
		return this.creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public String getDatosSesion() {
		return this.datosSesion;
	}

	public void setDatosSesion(String datosSesion) {
		this.datosSesion = datosSesion;
	}

	public Date getIngresoEn() {
		return this.ingresoEn;
	}

	public void setIngresoEn(Date ingresoEn) {
		this.ingresoEn = ingresoEn;
	}

	public Long getNumIntentos() {
		return this.numIntentos;
	}

	public void setNumIntentos(Long numIntentos) {
		this.numIntentos = numIntentos;
	}

	public String getSesionNavegador() {
		return this.sesionNavegador;
	}

	public void setSesionNavegador(String sesionNavegador) {
		this.sesionNavegador = sesionNavegador;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


}