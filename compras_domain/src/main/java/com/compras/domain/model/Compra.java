package com.compras.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import com.compras.commons.anotations.FieldValidate;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the compra database table.
 * 
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean activo;

	@FieldValidate
	private Long cantidad;

	@Column(name="direccion_envio")
	@FieldValidate
	private String direccionEnvio;

	@Column(name="fecha_maxima_entrega")
	@FieldValidate
	private Timestamp fechaMaximaEntrega;

	@Column(name="fecha_solicitud")
	@FieldValidate
	private Timestamp fechaSolicitud;
	
	@FieldValidate
	@Transient
	private Long idCliente;
	
	@JoinColumn(name="id_cliente")
	private Usuario cliente;

	@FieldValidate
	@Transient
	private Long idTienda;
	
	@JoinColumn(name="id_tienda")
	private Tienda tienda;

	private String observacion;
	
	@FieldValidate
	private String estado;

	@Column(name="tipo_pago")
	@FieldValidate
	private Long tipoPago;

	@FieldValidate
	private Long total;
	
	@FieldValidate
	@Transient
	private List<Producto> productos;

	public Compra() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public Timestamp getFechaMaximaEntrega() {
		return fechaMaximaEntrega;
	}

	public void setFechaMaximaEntrega(Timestamp fechaMaximaEntrega) {
		this.fechaMaximaEntrega = fechaMaximaEntrega;
	}

	public Timestamp getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(Long tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
}