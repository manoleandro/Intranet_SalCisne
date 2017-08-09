package br.com.sp.intranet.model.caixa;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
public class SaldoContaPK implements Serializable{
	
	private Date dia;
	private Long idConta;
	
	@Column(name="DIA", nullable=false)  
	public Date getDia() {
		return dia;
	}
	
	public void setDia(Date dia) {
		this.dia = dia;
	}

	@Column(name="ID_CONTA", nullable=false)
	public Long getIdConta() {
		return idConta;
	}
	
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
}
