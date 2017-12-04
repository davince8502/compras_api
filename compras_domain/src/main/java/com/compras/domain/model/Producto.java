package com.compras.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.compras.commons.anotations.FieldValidate;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean activo;

	@Column(name="codigo_barras")
	@FieldValidate
	private String codigoBarras;

	private String descripcion;
	
	@FieldValidate
	private String nombre;

	@FieldValidate
	private Double precio;
	
	

	

	public Producto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	
	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}