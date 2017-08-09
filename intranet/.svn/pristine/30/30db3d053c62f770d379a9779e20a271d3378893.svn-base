package br.com.sp.intranet.model.caixa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;


@Entity
@Table(name = "CONTAS")
@SequenceGenerator(name="SEQ_CONTAS",sequenceName="SEQ_CONTAS")
public class Contas extends GenericEntity{
	
	private Long idConta;
	private String descricao;
	private String conta;
	private int compSaldo;
	private TpConta tpConta;
	private Bancos banco;
	
	public Contas(){
		
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CONTAS")
	@Column(name = "ID_CONTA")
	public Long getIdConta() {
		return idConta;
	}
	
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	
	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "CONTA")
	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Column(name = "COMP_SALDO")
	public int getCompSaldo() {
		return compSaldo;
	}

	public void setCompSaldo(int compSaldo) {
		this.compSaldo = compSaldo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={})
	@JoinColumn(name = "ID_TP_CONTA")
	public TpConta getTpConta() {
		return tpConta;
	}

	public void setTpConta(TpConta tpConta) {
		this.tpConta = tpConta;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={})
	@JoinColumn(name = "ID_BANCO")
	public Bancos getBanco() {
		return banco;
	}

	public void setBanco(Bancos banco) {
		this.banco = banco;
	}
	
	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdConta();
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