package com.compras.domain.model.key;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tienda_producto database table.
 * 
 */
@Embeddable
public class TiendaProductoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="tienda_id")
	private Long tiendaId;

	@Column(name="producto_id")
	private Long productoId;

	public TiendaProductoPK() {
	}
	public Long getTiendaId() {
		return this.tiendaId;
	}
	public void setTiendaId(Long tiendaId) {
		this.tiendaId = tiendaId;
	}
	public Long getProductoId() {
		return this.productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TiendaProductoPK)) {
			return false;
		}
		TiendaProductoPK castOther = (TiendaProductoPK)other;
		return 
			this.tiendaId.equals(castOther.tiendaId)
			&& this.productoId.equals(castOther.productoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tiendaId.hashCode();
		hash = hash * prime + this.productoId.hashCode();
		
		return hash;
	}
}