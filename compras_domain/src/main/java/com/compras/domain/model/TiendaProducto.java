package com.compras.domain.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.compras.commons.anotations.FieldValidate;
import com.compras.domain.model.key.TiendaProductoPK;


/**
 * The persistent class for the tienda_producto database table.
 * 
 */
@Entity
@Table(name="tienda_producto")
@NamedQuery(name="TiendaProducto.findAll", query="SELECT t FROM TiendaProducto t")
public class TiendaProducto extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TiendaProductoPK id;	
	
	@ManyToOne
	@MapsId("tiendaId")
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;

	@ManyToOne
	@MapsId("productoId")
	@JoinColumn(name = "producto_id")
	private Producto producto;	

	private Boolean activo;
	
	@FieldValidate
	private Long cantidad;

	public TiendaProducto() {
	}
	
	

	public TiendaProductoPK getId() {
		return id;
	}

	public void setId(TiendaProductoPK id) {
		this.id = id;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
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
	
	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	
}