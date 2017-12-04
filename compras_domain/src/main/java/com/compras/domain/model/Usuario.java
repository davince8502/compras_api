package com.compras.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import com.compras.commons.anotations.FieldValidate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Timestamp birthday;

	@Column(name="cambio_clave")
	private Boolean cambioClave;

	@FieldValidate
	private String email;

	private Long estado;

	private String fullname;

	private Long genero;

	@Column(name="id_tipo_documento")
	@FieldValidate
	private Long idTipoDocumento;

	@FieldValidate
	private String nombres;

	@Column(name="numero_documento")
	@FieldValidate
	private String numeroDocumento;

	@FieldValidate
	private String password;

	@Column(name="primer_apellido")
	@FieldValidate
	private String primerApellido;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	@FieldValidate
	private String telefono;

	@FieldValidate
	private String username;

	
	//bi-directional many-to-one association to UsuarioRole
	@JsonIgnore
	@OneToMany(mappedBy="usuario",fetch = FetchType.LAZY)	
	private List<UsuarioRole> usuarioRoles;

	public Usuario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Boolean getCambioClave() {
		return this.cambioClave;
	}

	public void setCambioClave(Boolean cambioClave) {
		this.cambioClave = cambioClave;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Long getGenero() {
		return this.genero;
	}

	public void setGenero(Long genero) {
		this.genero = genero;
	}

	public Long getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UsuarioRole> getUsuarioRoles() {
		return this.usuarioRoles;
	}

	public void setUsuarioRoles(List<UsuarioRole> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

}