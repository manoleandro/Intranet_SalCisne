package br.com.sp.intranet.model.caixa;

import java.util.Date;
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
@Table(name = "LANCAMENTOS")
@SequenceGenerator(name="SEQ_LANCAMENTOS",sequenceName="SEQ_LANCAMENTOS")
public class Lancamentos extends GenericEntity{
	
	private Long idLancamento;
	private String descricao;
	private Double valor;
	private Date dtLancamento;
	private Date dataInicio;
	private Date dataFim;
	private Date dataInicioLancPermitido;
	private Date dataFimLancPermitido;
	private Contas contas;
	private String documento;
	private String debCred;
	private String strIdConta;
	private Integer contaFechada;
	private String strIdHistPadrao;
	
	
	public Lancamentos(){
		
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LANCAMENTOS")
	@Column(name = "ID_LANCAMENTO")
	public Long getIdLancamento() {
		return idLancamento;
	}
	public void setIdLancamento(Long idLancamento) {
		this.idLancamento = idLancamento;
	}
	
	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "VALOR")
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "DT_LANCAMENTO")
	public Date getDtLancamento() {
		return dtLancamento;
	}
	
	public void setDtLancamento(Date dtLancamento) {
		this.dtLancamento = dtLancamento;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={})
	@JoinColumn(name = "ID_CONTA")
	public Contas getContas() {
		return contas;
	}
	public void setContas(Contas contas) {
		this.contas = contas;
	}
		
	@Column(name = "DOCUMENTO")
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Column(name ="DEB_CRED")
	public String getDebCred() {
		return debCred;
	}

	public void setDebCred(String debCred) {
		this.debCred = debCred;
	}
	
	@Transient
	public String getStrIdConta() {
		return strIdConta;
	}

	public void setStrIdConta(String strIdConta) {
		this.strIdConta = strIdConta;
	}
	
	@Column(name = "CONTA_FECHADA")
	public Integer getContaFechada() {
		return contaFechada;
	}

	public void setContaFechada(Integer contaFechada) {
		this.contaFechada = contaFechada;
	}
	
	@Transient
	public String getStrIdHistPadrao() {
		return strIdHistPadrao;
	}

	public void setStrIdHistPadrao(String strIdHistPadrao) {
		this.strIdHistPadrao = strIdHistPadrao;
	}
	
	@Transient
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@Transient
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	@Transient
	public Date getDataInicioLancPermitido() {
		return dataInicioLancPermitido;
	}

	public void setDataInicioLancPermitido(Date dataInicioLancPermitido) {
		this.dataInicioLancPermitido = dataInicioLancPermitido;
	}
	
	@Transient
	public Date getDataFimLancPermitido() {
		return dataFimLancPermitido;
	}

	public void setDataFimLancPermitido(Date dataFimLancPermitido) {
		this.dataFimLancPermitido = dataFimLancPermitido;
	}
	
	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdLancamento();
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
