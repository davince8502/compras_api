package com.compras.domain.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.compras.domain.model.key.CompraProductoPK;


/**
 * The persistent class for the compra_producto database table.
 * 
 */
@Entity
@Table(name="compra_producto")
@NamedQuery(name="CompraProducto.findAll", query="SELECT c FROM CompraProducto c")
public class CompraProducto extends BaseEntity<Long>implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompraProductoPK id;
	
	
	@ManyToOne
	@MapsId("compraId")
	@JoinColumn(name = "compra_id")
	private Compra compra;

	@ManyToOne
	@MapsId("productoId")
	@JoinColumn(name = "producto_id")
	private Producto producto;

	private Boolean activo;


	public CompraProducto() {
	}


	public CompraProductoPK getId() {
		return id;
	}


	public void setId(CompraProductoPK id) {
		this.id = id;
	}


	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	

}