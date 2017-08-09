package br.com.sp.intranet.model.caixa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "SALDO_CONTA_DIA")
@SequenceGenerator(name="SEQ_SALDO_CONTA_DIA",sequenceName="SEQ_SALDO_CONTA_DIA")
public class SaldoConta extends GenericEntity{
	
	private SaldoContaPK pk;
	private Double saldo;
	private Double diferenca;
	private Date diaDB;
	private Long idContaDB;
	private Double valorLancDB;
	private String descricaoConta;
	
	public SaldoConta(){
		
	}
	
	@EmbeddedId
	public SaldoContaPK getPk() {
		return pk;
	}

	public void setPk(SaldoContaPK pk) {
		this.pk = pk;
	}
	
	@Column(name="SALDO", nullable = true)
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	} 
	
	@Transient
	public Double getValorLancDB() {
		return valorLancDB;
	}

	public void setValorLancDB(Double valorLancDB) {
		this.valorLancDB = valorLancDB;
	}

	@Transient
	public Date getDiaDB() {
		return diaDB;
	}

	public void setDiaDB(Date diaDB) {
		this.diaDB = diaDB;
	}
	
	@Transient
	public Long getIdContaDB() {
		return idContaDB;
	}

	public void setIdContaDB(Long idContaDB) {
		this.idContaDB = idContaDB;
	}

	@Transient
	public Double getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(Double diferenca) {
		this.diferenca = diferenca;
	}
	
	@Transient
	public String getDescricaoConta() {
		return descricaoConta;
	}

	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getPk();
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
