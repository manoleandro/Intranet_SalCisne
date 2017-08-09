package wsr.model.comercial;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import wsr.converter.LocalDateConverter;

@Entity
@Table(name = "PREVISAO_VENDAS")
public class PrevisaoVendas {
	
	private Long id;
	private Long idCliente;
	private LocalDate dataPrevisao;
	private Double valorPrevisao;
	private Long quantidadeKg;
	private Long dias;
	private Double precoMedio;
	private Long estoque;
	
	@Id
	@Column(name= "ID_PREVISAO_VENDAS")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name = "DATA_PREVISAO")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataPrevisao() {
		return dataPrevisao;
	}
	
	public void setDataPrevisao(LocalDate dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}
	
	@Column(name = "VALOR_PREVISAO")
	public Double getValorPrevisao() {
		return valorPrevisao;
	}
	
	public void setValorPrevisao(Double valorPrevisao) {
		this.valorPrevisao = valorPrevisao;
	}
	
	@Column(name = "QUANTIDE_KG")
	public Long getQuantidadeKg() {
		return quantidadeKg;
	}
	
	public void setQuantidadeKg(Long quantidadeKg) {
		this.quantidadeKg = quantidadeKg;
	}
	
	@Column(name = "DIAS")
	public Long getDias() {
		return dias;
	}
	
	public void setDias(Long dias) {
		this.dias = dias;
	}

	@Column(name = "PRECO_MEDIO")
	public Double getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(Double precoMedio) {
		this.precoMedio = precoMedio;
	}

	@Column(name = "ESTOQUE_FINAL")
	public Long getEstoque() {
		return estoque;
	}

	public void setEstoque(Long estoque) {
		this.estoque = estoque;
	}
}