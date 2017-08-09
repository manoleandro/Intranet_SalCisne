package br.com.sp.intranet.model.comercial;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class RegistroVisitaPK implements Serializable{
	
	private Long idCliente;
	private Date dataVisitaReal;
	
	public RegistroVisitaPK() {}
	
	public RegistroVisitaPK(Long idCliente, Date dataVisitaReal) {
		this.idCliente = idCliente;
		this.dataVisitaReal = dataVisitaReal;
	}

	@Column(name= "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	@Column(name="DATA_VISITA_REAL")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	public Date getDataVisitaReal() {
		return dataVisitaReal;
	}

	public void setDataVisitaReal(Date dataVisitaReal) {
		this.dataVisitaReal = dataVisitaReal;
	}
}