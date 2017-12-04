package com.compras.domain.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.compras.commons.anotations.FieldValidate;
import com.compras.domain.model.key.UsuarioRolePK;


/**
 * The persistent class for the usuario_role database table.
 * 
 */
@Entity
@Table(name="usuario_role")
@NamedQuery(name="UsuarioRole.findAll", query="SELECT u FROM UsuarioRole u")
public class UsuarioRole extends BaseEntity<Long> implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@FieldValidate
	private UsuarioRolePK id = new UsuarioRolePK();
	
	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name = "role_id")	
	private Role role;

	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	private Boolean activo;


	public UsuarioRole() {
	}
	
	@PrePersist
	private void preCreate(){
		
		if(this.activo == null){
			this.activo = true;
		}
		
	}

	public UsuarioRolePK getId() {
		return id;
	}

	public void setId(UsuarioRolePK id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}