package com.compras.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	private Timestamp creadoEn;

	@Column(name="datos_sesion")
	private String datosSesion;

	private Long estado;

	@Column(name="ingreso_en")
	private Timestamp ingresoEn;

	@Column(name="num_intentos")
	private Long numIntentos;

	@Column(name="sesion_navegador")
	private String sesionNavegador;

	private String token;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	
	

	public Sesion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreadoEn() {
		return this.creadoEn;
	}

	public void setCreadoEn(Timestamp creadoEn) {
		this.creadoEn = creadoEn;
	}

	public String getDatosSesion() {
		return this.datosSesion;
	}

	public void setDatosSesion(String datosSesion) {
		this.datosSesion = datosSesion;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Timestamp getIngresoEn() {
		return this.ingresoEn;
	}

	public void setIngresoEn(Timestamp ingresoEn) {
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}