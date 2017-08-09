package wsr.model.comercial;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import wsr.converter.LocalDateConverter;

@Entity
@Table(name = "WSR_PEDIDO_VENDA")
public class PedidoVenda implements Serializable{
	
	private Long idItemPedidoVendas;
	private Long idPedidoVendas;
	private Long numeroPedido;
	private Long idProduto;
	private Long quantidadeAtendida;
	private Double precoUnitario;
	private Long idFilial;
	private Long idCliente;
	private LocalDate dataEmissao;
	private String tipoTransporte;
	private Long idStatus;
	private Double pesoLiquido;
	
	@Id
	@Column(name = "ID_ITEM_PEDIDO_VENDAS")
	public Long getIdItemPedidoVendas() {
		return idItemPedidoVendas;
	}
	
	public void setIdItemPedidoVendas(Long idItemPedidoVendas) {
		this.idItemPedidoVendas = idItemPedidoVendas;
	}
	
	@Column(name = "ID_PEDIDO_VENDAS")
	public Long getIdPedidoVendas() {
		return idPedidoVendas;
	}
	
	public void setIdPedidoVendas(Long idPedidoVendas) {
		this.idPedidoVendas = idPedidoVendas;
	}
	
	@Column(name = "NUMERO_PEDIDO")
	public Long getNumeroPedido() {
		return numeroPedido;
	}
	
	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	@Column(name = "ID_PRODUTO")
	public Long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	@Column(name = "QUANTIDADE_ATENDIDA")
	public Long getQuantidadeAtendida() {
		return quantidadeAtendida;
	}
	
	public void setQuantidadeAtendida(Long quantidadeAtendida) {
		this.quantidadeAtendida = quantidadeAtendida;
	}
	
	@Column(name = "PRECO_UNITARIO")
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	
	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	@Column(name = "ID_FILIAL")
	public Long getIdFilial() {
		return idFilial;
	}
	
	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}
	
	@Column(name = "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name = "DATA_EMISSAO")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Column(name = "TIPO_TRANSPORTE")
	public String getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	
	@Column(name = "ID_STATUS")
	public Long getIdStatus() {
		return idStatus;
	}
	
	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	@Column(name = "PESO_LIQUIDO")
	public Double getPesoLiquido() {
		return pesoLiquido;
	}
	
	public void setPesoLiquido(Double pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}
}