package wsr.model.comercial;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import wsr.model.GenericEntity;
import wsr.util.DataTypes;

@Entity
@Table(name = "REGISTRO_VISITA")
public class RegistroVisita extends GenericEntity{

	private RegistroVisitaPK pk;
	
	//Vendedor
	private Long zona;
	private Long idVendedor;
	private String nomeVendedor;
	private Date dataVisitaSpv;
	
	//Cliente
	private String nomeCliente;
	private String praca;
	private Long diasReposicao;
	private Date dataSpv;
	
	//Previsao
	private Double previsaoPrecoCompra;
	private Long estoqueMesFindo;
	private Long estoqueProjetado;
	private Long entregasPendentes;
	private Long consumoKgDia;
	private Long diasCobertura;
	private Long necessidadeMes;
	private Long previsaoCompraKg;
	private Double previsaoCompraRs;
	private Date ultimaDataCompra;
	
	//Venda
	private Long vendaCompraKg;
	private Double vendaCompraRs;
	private Double vendaPrecoCompra;
	
	private Long compraKgEspecial;
	private Double compraRsEspecial;
	private Double precoCompraEspecial;
	
	private Double valorTotalPedido;
	
	private Long estoqueDiaVisita;
	private Long vendasConsumoKgDia;
	private Date dataProximaVisita;
	
	//Notas
	private Long variacaoConsumo;
	private Long variacaoDiasEmEstoque;
	
	private String comentarios;
	
	//verificacoes
	private boolean permiteEstoqueZero;
	private boolean alteracao;
	
	@EmbeddedId
	public RegistroVisitaPK getPk() {
		return pk;
	}

	public void setPk(RegistroVisitaPK pk) {
		this.pk = pk;
	}

	@Column(name = "ZONA")
	public Long getZona() {
		return zona;
	}

	public void setZona(Long zona) {
		this.zona = zona;
	}
	
	@Column(name = "ID_VENDEDOR")
	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	@Column(name = "NOME_VENDEDOR")
	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	@Column(name = "DATA_VISITA_SPV")
	public Date getDataVisitaSpv() {
		return dataVisitaSpv;
	}

	public void setDataVisitaSpv(Date dataVisitaSpv) {
		this.dataVisitaSpv = dataVisitaSpv;
	}
	
	@Column(name = "NOME_CLIENTE")
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Column(name = "PRACA")
	public String getPraca() {
		return praca;
	}

	public void setPraca(String praca) {
		this.praca = praca;
	}
	
	@Column(name = "DIAS_REPOSICAO")
	public Long getDiasReposicao() {
		return diasReposicao;
	}

	public void setDiasReposicao(Long diasReposicao) {
		this.diasReposicao = diasReposicao;
	}
	
	@Column(name = "DATA_SPV")
	public Date getDataSpv() {
		return dataSpv;
	}

	public void setDataSpv(Date dataSpv) {
		this.dataSpv = dataSpv;
	}
	
	@Column(name = "PREVISAO_PRECO_COMPRA")
	public Double getPrevisaoPrecoCompra() {
		return previsaoPrecoCompra;
	}

	public void setPrevisaoPrecoCompra(Double previsaoPrecoCompra) {
		this.previsaoPrecoCompra = previsaoPrecoCompra;
	}
	
	@Column(name = "ESTOQUE_MES_FINDO")
	public Long getEstoqueMesFindo() {
		return estoqueMesFindo;
	}

	public void setEstoqueMesFindo(Long estoqueMesFindo) {
		this.estoqueMesFindo = estoqueMesFindo;
	}

	@Column(name = "ESTOQUE_PROJETADO")
	public Long getEstoqueProjetado() {
		return estoqueProjetado;
	}

	public void setEstoqueProjetado(Long estoqueProjetado) {
		this.estoqueProjetado = estoqueProjetado;
	}
	
	@Column(name = "ENTREGAS_PENDENTES")
	public Long getEntregasPendentes() {
		return entregasPendentes;
	}

	public void setEntregasPendentes(Long entregasPendentes) {
		this.entregasPendentes = entregasPendentes;
	}
	
	@Column(name = "CONSUMO_KG_DIA")
	public Long getConsumoKgDia() {
		return consumoKgDia;
	}

	public void setConsumoKgDia(Long consumoKgDia) {
		this.consumoKgDia = consumoKgDia;
	}
	
	@Column(name = "DIAS_COBERTURA")
	public Long getDiasCobertura() {
		return diasCobertura;
	}

	public void setDiasCobertura(Long diasCobertura) {
		this.diasCobertura = diasCobertura;
	}
	
	@Column(name = "NECESSIDADE_MES")
	public Long getNecessidadeMes() {
		return necessidadeMes;
	}

	public void setNecessidadeMes(Long necessidadeMes) {
		this.necessidadeMes = necessidadeMes;
	}
	
	@Column(name = "PREVISAO_COMPRA_KG")
	public Long getPrevisaoCompraKg() {
		return previsaoCompraKg;
	}

	public void setPrevisaoCompraKg(Long previsaoCompraKg) {
		this.previsaoCompraKg = previsaoCompraKg;
	}

	@Column(name = "PREVISAO_COMPRA_RS")
	public Double getPrevisaoCompraRs() {
		return previsaoCompraRs;
	}

	public void setPrevisaoCompraRs(Double previsaoCompraRs) {
		this.previsaoCompraRs = previsaoCompraRs;
	}

	@Column(name = "ULTIMA_DATA_COMPRA")
	public Date getUltimaDataCompra() {
		return ultimaDataCompra;
	}
	
	public void setUltimaDataCompra(Date ultimaDataCompra) {
		this.ultimaDataCompra = ultimaDataCompra;
	}

	@Column(name = "VENDA_COMPRA_KG")
	public Long getVendaCompraKg() {
		return vendaCompraKg;
	}

	public void setVendaCompraKg(Long vendaCompraKg) {
		this.vendaCompraKg = vendaCompraKg;
	}

	@Column(name = "VENDA_COMPRA_RS")
	public Double getVendaCompraRs() {
		return vendaCompraRs;
	}

	public void setVendaCompraRs(Double vendaCompraRs) {
		this.vendaCompraRs = vendaCompraRs;
	}
	
	@Column(name = "VENDA_PRECO_COMPRA")
	public Double getVendaPrecoCompra() {
		return vendaPrecoCompra;
	}

	public void setVendaPrecoCompra(Double vendaPrecoCompra) {
		this.vendaPrecoCompra = vendaPrecoCompra;
	}

	@Column(name = "ESTOQUE_DIA_VISITA")
	public Long getEstoqueDiaVisita() {
		return estoqueDiaVisita;
	}

	public void setEstoqueDiaVisita(Long estoqueDiaVisita) {
		this.estoqueDiaVisita = estoqueDiaVisita;
	}

	@Column(name = "VENDAS_CONSUMO_KG_DIA")
	public Long getVendasConsumoKgDia() {
		return vendasConsumoKgDia;
	}

	public void setVendasConsumoKgDia(Long vendasConsumoKgDia) {
		this.vendasConsumoKgDia = vendasConsumoKgDia;
	}

	@Column(name = "DATA_PROXIMA_VISITA")
	public Date getDataProximaVisita() {
		return dataProximaVisita;
	}

	public void setDataProximaVisita(Date dataProximaVisita) {
		this.dataProximaVisita = dataProximaVisita;
	}

	@Column(name = "VARIACAO_CONSUMO")
	public Long getVariacaoConsumo() {
		return variacaoConsumo;
	}

	public void setVariacaoConsumo(Long variacaoConsumo) {
		this.variacaoConsumo = variacaoConsumo;
	}
	
	@Column(name = "VARIACAO_DIAS_ESTOQUE")
	public Long getVariacaoDiasEmEstoque() {
		return variacaoDiasEmEstoque;
	}

	public void setVariacaoDiasEmEstoque(Long variacaoDiasEmEstoque) {
		this.variacaoDiasEmEstoque = variacaoDiasEmEstoque;
	}
	
	@Column(name = "COMENTARIOS")
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	@Column(name = "COMPRA_KG_ESPECIAL")
	public Long getCompraKgEspecial() {
		return compraKgEspecial;
	}

	public void setCompraKgEspecial(Long compraKgEspecial) {
		this.compraKgEspecial = compraKgEspecial;
	}

	@Column(name = "COMPRA_RS_ESPECIAL")
	public Double getCompraRsEspecial() {
		return compraRsEspecial;
	}

	public void setCompraRsEspecial(Double compraRsEspecial) {
		this.compraRsEspecial = compraRsEspecial;
	}

	@Column(name = "PRECO_COMPRA_ESPECIAL")
	public Double getPrecoCompraEspecial() {
		return precoCompraEspecial;
	}

	public void setPrecoCompraEspecial(Double precoCompraEspecial) {
		this.precoCompraEspecial = precoCompraEspecial;
	}

	@Transient
	public Double getValorTotalPedido() {
		valorTotalPedido = DataTypes.parseNull(getVendaCompraRs()) + DataTypes.parseNull(getCompraRsEspecial());
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	@Transient
	public boolean isPermiteEstoqueZero() {
		return permiteEstoqueZero;
	}

	public void setPermiteEstoqueZero(boolean permiteEstoqueZero) {
		this.permiteEstoqueZero = permiteEstoqueZero;
	}
	
	@Transient
	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroVisita other = (RegistroVisita) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		return getPk();
	}
}