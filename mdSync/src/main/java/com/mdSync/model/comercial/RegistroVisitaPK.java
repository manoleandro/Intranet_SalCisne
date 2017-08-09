package com.mdSync.model.comercial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegistroVisitaPK implements Serializable{
	
	private Long idCliente;
	private String dataVisitaReal;
	
	@Column(name= "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name="DATA_VISITA_REAL")
	public String getDataVisitaReal() {
		return dataVisitaReal;
	}
	public void setDataVisitaReal(String dataVisitaReal) {
		this.dataVisitaReal = dataVisitaReal;
	}
}