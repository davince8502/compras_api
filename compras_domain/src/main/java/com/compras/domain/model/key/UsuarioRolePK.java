package com.compras.domain.model.key;

import java.io.Serializable;
import javax.persistence.*;

import com.compras.commons.anotations.FieldValidate;

/**
 * The primary key class for the usuario_role database table.
 * 
 */
@Embeddable
public class UsuarioRolePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", insertable=false, updatable=false)
	@FieldValidate
	private Long roleId;

	@Column(name="usuario_id", insertable=false, updatable=false)
	@FieldValidate
	private Long usuarioId;

	public UsuarioRolePK() {
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getUsuarioId() {
		return this.usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioRolePK)) {
			return false;
		}
		UsuarioRolePK castOther = (UsuarioRolePK)other;
		return 
			this.roleId.equals(castOther.roleId)
			&& this.usuarioId.equals(castOther.usuarioId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.usuarioId.hashCode();
		
		return hash;
	}
}