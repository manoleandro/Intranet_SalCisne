package wsr.model.comercial;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="HIST_PLANEJAMENTO_VENDAS")
@SequenceGenerator(name="SEQ_HIST_PLANEJAMENTO_VENDAS",sequenceName="SEQ_HIST_PLANEJAMENTO_VENDAS")
public class HistPlanejamentoVendas implements Serializable{

	private Long id;
	private String mes;
	private Long idCliente;
	private Integer quantidadeEstoque;
	private Date dataLancEstoque;
	private String origemEstoque;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HIST_PLANEJAMENTO_VENDAS")
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="MES")
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	@Column(name="ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name="QUANTIDADE_ESTOQUE")
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	@Column(name="DATA_LANC_ESTOQUE")
	public Date getDataLancEstoque() {
		return dataLancEstoque;
	}
	
	public void setDataLancEstoque(Date dataLancEstoque) {
		this.dataLancEstoque = dataLancEstoque;
	}
	
	@Column(name="ORIGEM_ESTOQUE")
	public String getOrigemEstoque() {
		return origemEstoque;
	}
	public void setOrigemEstoque(String origemEstoque) {
		this.origemEstoque = origemEstoque;
	}
}