package br.com.sp.intranet.model.comercial;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.util.DataTypes;

@Entity
@Table(name = "ROTEIRO_VISITA")
@SequenceGenerator(name="SEQ_ROTEIRO_VISITA",sequenceName="SEQ_ROTEIRO_VISITA")
public class RoteiroVisita extends GenericEntity {
	
	private Long id;
	private Long idCliente;
	private Date dataVisita;
	private SituacaoRoteiroVisitaEnum situacao;
	private Long zonaVendas;
	private String nomeCliente;
	private Long consumoProgressivo;
	private Long diasCobertura;
	private Long quantidadeEstoque;
	private Long quantidadePrevisao;
	private Double precoMedio;
	private Double valorPrevisao;
	private Double valorPrevisaoEspecial;
	private Double valorPrevisaoTotal;

	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ROTEIRO_VISITA")
	@Column(name = "ID")
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

	@Column(name = "DATA_VISITA")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	@Column(name = "ZONA_VENDAS")
	public Long getZonaVendas() {
		return zonaVendas;
	}

	public void setZonaVendas(Long zonaVendas) {
		this.zonaVendas = zonaVendas;
	}

	@Column(name = "SITUACAO")
	@Enumerated(EnumType.STRING)
	public SituacaoRoteiroVisitaEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoRoteiroVisitaEnum situacao) {
		this.situacao = situacao;
	}

	@Column(name = "NOME_CLIENTE")
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Column(name = "CONSUMO_PROGRESSIVO")
	public Long getConsumoProgressivo() {
		return DataTypes.parseNull(consumoProgressivo);
	}

	public void setConsumoProgressivo(Long consumoProgressivo) {
		this.consumoProgressivo = consumoProgressivo;
	}

	@Column(name = "DIAS_COBERTURA")
	public Long getDiasCobertura() {
		return DataTypes.parseNull(diasCobertura);
	}

	public void setDiasCobertura(Long diasCobertura) {
		this.diasCobertura = diasCobertura;
	}

	@Column(name = "QUANTIDADE_ESTOQUE")
	public Long getQuantidadeEstoque() {
		return DataTypes.parseNull(quantidadeEstoque);
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	@Column(name = "QUANTIDADE_PREVISAO")
	public Long getQuantidadePrevisao() {
		return DataTypes.parseNull(quantidadePrevisao);
	}

	public void setQuantidadePrevisao(Long quantidadePrevisao) {
		this.quantidadePrevisao = quantidadePrevisao;
	}

	@Column(name = "PRECO_MEDIO")
	public Double getPrecoMedio() {
		return DataTypes.parseNull(precoMedio);
	}

	public void setPrecoMedio(Double precoMedio) {
		this.precoMedio = precoMedio;
	}

	@Column(name = "VALOR_PREVISAO")
	public Double getValorPrevisao() {
		return DataTypes.parseNull(valorPrevisao);
	}

	public void setValorPrevisao(Double valorPrevisao) {
		this.valorPrevisao = valorPrevisao;
	}

	@Column(name = "VALOR_PREVISAO_ESPECIAL")
	public Double getValorPrevisaoEspecial() {
		return DataTypes.parseNull(valorPrevisaoEspecial);
	}

	public void setValorPrevisaoEspecial(Double valorPrevisaoEspecial) {
		this.valorPrevisaoEspecial = valorPrevisaoEspecial;
	}
	
	@Column(name = "VALOR_PREVISAO_TOTAL")
	public Double getValorPrevisaoTotal() {
		return getValorPrevisao() + getValorPrevisaoEspecial();
	}

	public void setValorPrevisaoTotal(Double valorPrevisaoTotal) {
		this.valorPrevisaoTotal = valorPrevisaoTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consumoProgressivo == null) ? 0 : consumoProgressivo.hashCode());
		result = prime * result + ((dataVisita == null) ? 0 : dataVisita.hashCode());
		result = prime * result + ((diasCobertura == null) ? 0 : diasCobertura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime * result + ((precoMedio == null) ? 0 : precoMedio.hashCode());
		result = prime * result + ((quantidadeEstoque == null) ? 0 : quantidadeEstoque.hashCode());
		result = prime * result + ((quantidadePrevisao == null) ? 0 : quantidadePrevisao.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((valorPrevisao == null) ? 0 : valorPrevisao.hashCode());
		result = prime * result + ((valorPrevisaoEspecial == null) ? 0 : valorPrevisaoEspecial.hashCode());
		result = prime * result + ((valorPrevisaoTotal == null) ? 0 : valorPrevisaoTotal.hashCode());
		result = prime * result + ((zonaVendas == null) ? 0 : zonaVendas.hashCode());
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
		RoteiroVisita other = (RoteiroVisita) obj;
		if (consumoProgressivo == null) {
			if (other.consumoProgressivo != null)
				return false;
		} else if (!consumoProgressivo.equals(other.consumoProgressivo))
			return false;
		if (dataVisita == null) {
			if (other.dataVisita != null)
				return false;
		} else if (!dataVisita.equals(other.dataVisita))
			return false;
		if (diasCobertura == null) {
			if (other.diasCobertura != null)
				return false;
		} else if (!diasCobertura.equals(other.diasCobertura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		if (precoMedio == null) {
			if (other.precoMedio != null)
				return false;
		} else if (!precoMedio.equals(other.precoMedio))
			return false;
		if (quantidadeEstoque == null) {
			if (other.quantidadeEstoque != null)
				return false;
		} else if (!quantidadeEstoque.equals(other.quantidadeEstoque))
			return false;
		if (quantidadePrevisao == null) {
			if (other.quantidadePrevisao != null)
				return false;
		} else if (!quantidadePrevisao.equals(other.quantidadePrevisao))
			return false;
		if (situacao != other.situacao)
			return false;
		if (valorPrevisao == null) {
			if (other.valorPrevisao != null)
				return false;
		} else if (!valorPrevisao.equals(other.valorPrevisao))
			return false;
		if (valorPrevisaoEspecial == null) {
			if (other.valorPrevisaoEspecial != null)
				return false;
		} else if (!valorPrevisaoEspecial.equals(other.valorPrevisaoEspecial))
			return false;
		if (valorPrevisaoTotal == null) {
			if (other.valorPrevisaoTotal != null)
				return false;
		} else if (!valorPrevisaoTotal.equals(other.valorPrevisaoTotal))
			return false;
		if (zonaVendas == null) {
			if (other.zonaVendas != null)
				return false;
		} else if (!zonaVendas.equals(other.zonaVendas))
			return false;
		return true;
	}

	@Override
	@Transient
	@JsonIgnore
	public Object getPrimaryKey() {
		return getId();
	}
}