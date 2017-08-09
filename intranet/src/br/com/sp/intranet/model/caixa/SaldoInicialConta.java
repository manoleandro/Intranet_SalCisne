package br.com.sp.intranet.model.caixa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name="SALDO_INICIAL_CONTA")
public class SaldoInicialConta extends GenericEntity{
	
	private Long id;
	private Contas conta;
	private Double saldo;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTA")
	public Contas getConta() {
		return conta;
	}
	
	public void setConta(Contas conta) {
		this.conta = conta;
	}
	
	@Column(name="SALDO")
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	@Transient
	@Override
	public Object getPrimaryKey() {
		return getId();
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