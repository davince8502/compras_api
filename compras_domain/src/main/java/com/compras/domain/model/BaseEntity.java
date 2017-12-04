package com.compras.domain.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity<U>  {    
    
    @Column(name="creado_en")
    @CreatedDate
    @Temporal(TIMESTAMP)
	protected Date creadoEn;
    
    @Column(name="creado_por")
    @CreatedBy
    protected U creadoPor;    

	@Column(name="modificado_en")
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date modificadoEn;	
	
	@Column(name="modificado_por")
	@LastModifiedBy
	protected U modificadoPor;

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public U getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(U creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getModificadoEn() {
		return modificadoEn;
	}

	public void setModificadoEn(Date modificadoEn) {
		this.modificadoEn = modificadoEn;
	}

	public U getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(U modificadoPor) {
		this.modificadoPor = modificadoPor;
	}    
    
}
