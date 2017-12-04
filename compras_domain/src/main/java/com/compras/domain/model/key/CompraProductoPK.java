package com.compras.domain.model.key;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the compra_producto database table.
 * 
 */
@Embeddable
public class CompraProductoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="compra_id")
	private Long compraId;

	@Column(name="producto_id")
	private Long productoId;

	public CompraProductoPK() {
	}
	public Long getCompraId() {
		return this.compraId;
	}
	public void setCompraId(Long compraId) {
		this.compraId = compraId;
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
		if (!(other instanceof CompraProductoPK)) {
			return false;
		}
		CompraProductoPK castOther = (CompraProductoPK)other;
		return 
			this.compraId.equals(castOther.compraId)
			&& this.productoId.equals(castOther.productoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.compraId.hashCode();
		hash = hash * prime + this.productoId.hashCode();
		
		return hash;
	}
}