package wsr.model.comercial;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import wsr.converter.LocalDateConverter;
import wsr.model.GenericEntity;

@Entity
@Table(name = "ESTOQUE_CLIENTE")
public class EstoqueCliente extends GenericEntity {

	private Long idEstoqueCliente;
	private Cliente cliente;
	private Long idProduto;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private LocalDate dataLancto;
	private Integer quantidadeLancada;
	private Integer quantidadeEstoque;
	private Long consumoEntreDatas;
	private Long consumoProgressivo;
	private String dataRuptura;
	private LocalDate dataAlerta;
	private Double mediaMovel;

	public EstoqueCliente() {
	}

	@Id
	@Column(name = "ID_ESTOQUE_CLIENTE")
	public Long getIdEstoqueCliente() {
		return this.idEstoqueCliente;
	}

	public void setIdEstoqueCliente(Long idEstoqueCliente) {
		this.idEstoqueCliente = idEstoqueCliente;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE")
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column(name = "ID_PRODUTO")
	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	@Column(name = "QUANTIDADE_LANCADA")
	public Integer getQuantidadeLancada() {
		return this.quantidadeLancada;
	}

	public void setQuantidadeLancada(Integer quantidadeLancada) {
		this.quantidadeLancada = quantidadeLancada;
	}

	@Column(name = "QUANTIDADE_ESTOQUE")
	public Integer getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	@Column(name = "CONSUMO_ENTRE_DATAS")
	public Long getConsumoEntreDatas() {
		return this.consumoEntreDatas;
	}

	public void setConsumoEntreDatas(Long consumoEntreDatas) {
		this.consumoEntreDatas = consumoEntreDatas;
	}

	@Column(name = "CONSUMO_PROGRESSIVO")
	public Long getConsumoProgressivo() {
		return this.consumoProgressivo;
	}

	public void setConsumoProgressivo(Long consumoProgressivo) {
		this.consumoProgressivo = consumoProgressivo;
	}

	@Column(name = "DATA_RUPTURA")
	public String getDataRuptura() {
		return this.dataRuptura;
	}

	public void setDataRuptura(String dataRuptura) {
		this.dataRuptura = dataRuptura;
	}

	@Column(name = "MEDIA_MOVEL")
	public Double getMediaMovel() {
		return this.mediaMovel;
	}

	public void setMediaMovel(Double mediaMovel) {
		this.mediaMovel = mediaMovel;
	}
	
	@Column(name="DATA_INICIO")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Column(name="DATA_FIM")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	@Column(name="DATA_LANCTO")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataLancto() {
		return dataLancto;
	}

	public void setDataLancto(LocalDate dataLancto) {
		this.dataLancto = dataLancto;
	}

	@Column(name="DATA_ALERTA")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataAlerta() {
		return dataAlerta;
	}

	public void setDataAlerta(LocalDate dataAlerta) {
		this.dataAlerta = dataAlerta;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdEstoqueCliente();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstoqueCliente == null) ? 0 : idEstoqueCliente.hashCode());
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
		EstoqueCliente other = (EstoqueCliente) obj;
		if (idEstoqueCliente == null) {
			if (other.idEstoqueCliente != null)
				return false;
		} else if (!idEstoqueCliente.equals(other.idEstoqueCliente))
			return false;
		return true;
	}
}	