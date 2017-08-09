package br.com.sp.intranet.model.comercial;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "DESPESA_VISITA")
@SequenceGenerator(name = "SEQ_DESPESA_VISITA", sequenceName = "SEQ_DESPESA_VISITA")
public class DespesaVisita extends GenericEntity {

	private Long id;
	private Date data;
	private List<DespesaVisitaCliente> visitaClientes;
	private Long quantidadeKm;
	private Long quantidadeDiarias;
	private Long quantidadeRefeicoes;
	private Double outrosGastos;
	private String comentarios;
	private Long zonaVendas;
	private String deslocamento;
	private Double totalDespesa;
	
	public DespesaVisita() {}
	
	public DespesaVisita(Date data, List<DespesaVisitaCliente> visitaClientes, Long zonaVendas) {
		this.data = data;
		this.visitaClientes = visitaClientes;
		this.zonaVendas = zonaVendas;
	}

	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DESPESA_VISITA")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DATA")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_DESPESA_VISITA")
	public List<DespesaVisitaCliente> getVisitaClientes() {
		return visitaClientes;
	}

	public void setVisitaClientes(List<DespesaVisitaCliente> visitaClientes) {
		this.visitaClientes = visitaClientes;
	}
	
	@Column(name = "QUANTIDADE_KM")
	public Long getQuantidadeKm() {
		return quantidadeKm;
	}

	public void setQuantidadeKm(Long quantidadeKm) {
		this.quantidadeKm = quantidadeKm;
	}
	
	@Column(name = "QUANTIDADE_DIARIAS")
	public Long getQuantidadeDiarias() {
		return quantidadeDiarias;
	}

	public void setQuantidadeDiarias(Long quantidadeDiarias) {
		this.quantidadeDiarias = quantidadeDiarias;
	}

	@Column(name = "QUANTIDADE_REFEICOES")
	public Long getQuantidadeRefeicoes() {
		return quantidadeRefeicoes;
	}

	public void setQuantidadeRefeicoes(Long quantidadeRefeicoes) {
		this.quantidadeRefeicoes = quantidadeRefeicoes;
	}

	@Column(name = "OUTROS_GASTOS")
	public Double getOutrosGastos() {
		return outrosGastos;
	}

	public void setOutrosGastos(Double outrosGastos) {
		this.outrosGastos = outrosGastos;
	}

	@Column(name = "COMENTARIOS")
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	@Column(name = "ZONA_VENDAS")
	public Long getZonaVendas() {
		return zonaVendas;
	}

	public void setZonaVendas(Long zonaVendas) {
		this.zonaVendas = zonaVendas;
	}

	@Transient
	public String getDeslocamento() {
		deslocamento = visitaClientes.stream().sorted().map(c -> c.getCliente().getNomeMunicipio()).collect(Collectors.joining("/"));
		
		return deslocamento;
	}

	public void setDeslocamento(String deslocamento) {
		this.deslocamento = deslocamento;
	}

	@Column(name = "TOTAL_DESPESA")
	public Double getTotalDespesa() {
		return totalDespesa;
	}

	public void setTotalDespesa(Double totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((deslocamento == null) ? 0 : deslocamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((outrosGastos == null) ? 0 : outrosGastos.hashCode());
		result = prime * result + ((quantidadeDiarias == null) ? 0 : quantidadeDiarias.hashCode());
		result = prime * result + ((quantidadeKm == null) ? 0 : quantidadeKm.hashCode());
		result = prime * result + ((quantidadeRefeicoes == null) ? 0 : quantidadeRefeicoes.hashCode());
		result = prime * result + ((totalDespesa == null) ? 0 : totalDespesa.hashCode());
		result = prime * result + ((visitaClientes == null) ? 0 : visitaClientes.hashCode());
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
		DespesaVisita other = (DespesaVisita) obj;
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (deslocamento == null) {
			if (other.deslocamento != null)
				return false;
		} else if (!deslocamento.equals(other.deslocamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (outrosGastos == null) {
			if (other.outrosGastos != null)
				return false;
		} else if (!outrosGastos.equals(other.outrosGastos))
			return false;
		if (quantidadeDiarias == null) {
			if (other.quantidadeDiarias != null)
				return false;
		} else if (!quantidadeDiarias.equals(other.quantidadeDiarias))
			return false;
		if (quantidadeKm == null) {
			if (other.quantidadeKm != null)
				return false;
		} else if (!quantidadeKm.equals(other.quantidadeKm))
			return false;
		if (quantidadeRefeicoes == null) {
			if (other.quantidadeRefeicoes != null)
				return false;
		} else if (!quantidadeRefeicoes.equals(other.quantidadeRefeicoes))
			return false;
		if (totalDespesa == null) {
			if (other.totalDespesa != null)
				return false;
		} else if (!totalDespesa.equals(other.totalDespesa))
			return false;
		if (visitaClientes == null) {
			if (other.visitaClientes != null)
				return false;
		} else if (!visitaClientes.equals(other.visitaClientes))
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