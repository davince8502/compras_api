package com.compras.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.compras.commons.anotations.FieldValidate;


/**
 * The persistent class for the tienda database table.
 * 
 */
@Entity
@NamedQuery(name="Tienda.findAll", query="SELECT t FROM Tienda t")
public class Tienda extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean activo;

	@FieldValidate
	private String direccion;

	@FieldValidate
	private String horario;

	@FieldValidate
	private String nombre;
	
	@Transient
	private List<Producto> productos;

	public Tienda() {
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

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}