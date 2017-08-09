package wsr.model.comercial;

import java.time.LocalDate;
import java.util.Date;

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
@Table(name = "MV_EST_CL")
public class MvEstCl extends GenericEntity {
	private Long idMvEstCl;
	private Cliente cliente;
	private Long idProduto;
	private LocalDate dataLancto;
	private Double quantidade;
	private Long procesEstoque;
	private Long numeroNf;

	public MvEstCl() {}

	@Id
	@Column(name = "ID_MV_EST_CL", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdMvEstCl() {
		return this.idMvEstCl;
	}

	public void setIdMvEstCl(Long idMvEstCl) {
		this.idMvEstCl = idMvEstCl;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE", unique = false, nullable = true, insertable = true, updatable = true)
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

	@Column(name = "DATA_LANCTO", unique = false, nullable = false, insertable = true, updatable = true)
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataLancto() {
		return dataLancto;
	}

	public void setDataLancto(LocalDate dataLancto) {
		this.dataLancto = dataLancto;
	}

	@Column(name = "QUANTIDADE", unique = false, nullable = false, insertable = true, updatable = true)
	public Double getQuantidade() {
		return this.quantidade;
	}

	
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "PROCES_ESTOQUE", unique = false, nullable = true, insertable = true, updatable = true, precision = 1, scale = 0)
	public Long getProcesEstoque() {
		return this.procesEstoque;
	}

	public void setProcesEstoque(Long procesEstoque) {
		this.procesEstoque = procesEstoque;
	}

	@Column(name = "NUMERO_NF", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getNumeroNf() {
		return this.numeroNf;
	}

	public void setNumeroNf(Long numeroNf) {
		this.numeroNf = numeroNf;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdMvEstCl();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMvEstCl == null) ? 0 : idMvEstCl.hashCode());
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
		MvEstCl other = (MvEstCl) obj;
		if (idMvEstCl == null) {
			if (other.idMvEstCl != null)
				return false;
		} else if (!idMvEstCl.equals(other.idMvEstCl))
			return false;
		return true;
	}
}