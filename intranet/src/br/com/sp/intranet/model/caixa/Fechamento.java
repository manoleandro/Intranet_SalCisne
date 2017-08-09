package br.com.sp.intranet.model.caixa;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "FECHAMENTO")
public class Fechamento extends GenericEntity{
	
	private Date competencia;
	private Double saldo;
	private String periodo;
	private String compSaldo;
	private Long idConta;
	
	public Fechamento(){
		
	}
	@Id
	@Column(name = "COMPETENCIA", unique = true, nullable = false, insertable = true, updatable = true)
	public Date getCompetencia() {
		return competencia;
	}
	
	public void setCompetencia(Date competencia) {
		this.competencia = competencia;
	}
	
	@Column(name = "SALDO", unique = false, nullable = false, insertable = true, updatable = true)
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	@Column(name = "PERIODO", unique = false, nullable = true, insertable = true, updatable = true)
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
	@Transient
	public String getCompSaldo() {
		return compSaldo;
	}
	public void setCompSaldo(String compSaldo) {
		this.compSaldo = compSaldo;
	}
	
	@Transient
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	
	@Override
	@Transient
	public Object getPrimaryKey() {
		return getCompetencia();
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
