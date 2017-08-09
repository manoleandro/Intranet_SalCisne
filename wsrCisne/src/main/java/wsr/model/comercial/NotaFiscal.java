package wsr.model.comercial;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WSR_NOTA_FISCAL")
public class NotaFiscal implements Serializable{
	
	private Long idItemNotaFiscal;
	private Long idNotaFiscal;
	private Long numeroNf;
	private Date dataEmissao;
	private Long numeroPedido;
	private Date dataCanhoto;
	private Long idCliente;
	private Long quantidade;
	private Double valorUnitario;
	private Double percDesconto;
	private Double pesoLiquido;
	
	@Id
	@Column(name ="ID_IT_NF_FISCAL")
	public Long getIdItemNotaFiscal() {
		return idItemNotaFiscal;
	}
	
	public void setIdItemNotaFiscal(Long idItemNotaFiscal) {
		this.idItemNotaFiscal = idItemNotaFiscal;
	}
	
	@Column(name ="ID_NOTA_FISCAL")
	public Long getIdNotaFiscal() {
		return idNotaFiscal;
	}
	
	public void setIdNotaFiscal(Long idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}
	
	@Column(name ="NUMERO_NF")
	public Long getNumeroNf() {
		return numeroNf;
	}
	
	public void setNumeroNf(Long numeroNf) {
		this.numeroNf = numeroNf;
	}
	
	@Column(name ="DATA_EMISSAO")
	public Date getDataEmissao() {
		return dataEmissao;
	}
	
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	@Column(name ="NUMERO_PEDIDO")
	public Long getNumeroPedido() {
		return numeroPedido;
	}
	
	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	@Column(name ="DATA_CANHOTO")
	public Date getDataCanhoto() {
		return dataCanhoto;
	}
	
	public void setDataCanhoto(Date dataCanhoto) {
		this.dataCanhoto = dataCanhoto;
	}
	
	@Column(name ="ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name ="QUANTIDADE")
	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name ="VALOR_UNITARIO")
	public Double getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	@Column(name ="PERC_DESCONTO")
	public Double getPercDesconto() {
		return percDesconto;
	}
	
	public void setPercDesconto(Double percDesconto) {
		this.percDesconto = percDesconto;
	}
	
	@Column(name ="PESO_LIQUIDO")
	public Double getPesoLiquido() {
		return pesoLiquido;
	}
	
	public void setPesoLiquido(Double pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}
}