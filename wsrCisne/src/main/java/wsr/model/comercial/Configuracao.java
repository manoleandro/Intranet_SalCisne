package wsr.model.comercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import wsr.model.GenericEntity;

@Entity
@Table(name = "CONFIGURACAO")
public class Configuracao extends GenericEntity {

	private Long id;
	private Double valorKm;
	private Double valorDiaria;
	private Double valorRefeicao;

	public Configuracao() {}

	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "VALOR_KM")
	public Double getValorKm() {
		return valorKm;
	}

	public void setValorKm(Double valorKm) {
		this.valorKm = valorKm;
	}
	
	@Column(name = "VALOR_DIARIA")
	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
	@Column(name = "VALOR_REFEICAO")
	public Double getValorRefeicao() {
		return valorRefeicao;
	}

	public void setValorRefeicao(Double valorRefeicao) {
		this.valorRefeicao = valorRefeicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valorDiaria == null) ? 0 : valorDiaria.hashCode());
		result = prime * result + ((valorKm == null) ? 0 : valorKm.hashCode());
		result = prime * result + ((valorRefeicao == null) ? 0 : valorRefeicao.hashCode());
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
		Configuracao other = (Configuracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valorDiaria == null) {
			if (other.valorDiaria != null)
				return false;
		} else if (!valorDiaria.equals(other.valorDiaria))
			return false;
		if (valorKm == null) {
			if (other.valorKm != null)
				return false;
		} else if (!valorKm.equals(other.valorKm))
			return false;
		if (valorRefeicao == null) {
			if (other.valorRefeicao != null)
				return false;
		} else if (!valorRefeicao.equals(other.valorRefeicao))
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