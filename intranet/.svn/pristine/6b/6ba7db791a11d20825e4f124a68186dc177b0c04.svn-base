package br.com.sp.intranet.model.comercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "DESPESA_VISITA_CLIENTE")
@SequenceGenerator(name = "SEQ_DESPESA_VISITA_CLIENTE", sequenceName = "SEQ_DESPESA_VISITA_CLIENTE")
public class DespesaVisitaCliente extends GenericEntity implements Comparable<DespesaVisitaCliente>{

	private Long id;
	private Long idDespesaVisita;
	private Cliente cliente;
	private Integer ordem;
	
	public DespesaVisitaCliente() {}
	
	public DespesaVisitaCliente(Long idDespesaVisita, Cliente cliente, Integer ordem) {
		this.cliente = cliente;
		this.ordem = ordem;
		this.idDespesaVisita = idDespesaVisita;
	}

	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DESPESA_VISITA_CLIENTE")
	@Column(name ="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ID_DESPESA_VISITA")
	public Long getIdDespesaVisita() {
		return idDespesaVisita;
	}

	public void setIdDespesaVisita(Long idDespesaVisita) {
		this.idDespesaVisita = idDespesaVisita;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CLIENTE")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column(name = "ORDEM")
	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	@Transient
	@JsonIgnore
	public Object getPrimaryKey() {
		return getId();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idDespesaVisita == null) ? 0 : idDespesaVisita.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
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
		DespesaVisitaCliente other = (DespesaVisitaCliente) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idDespesaVisita == null) {
			if (other.idDespesaVisita != null)
				return false;
		} else if (!idDespesaVisita.equals(other.idDespesaVisita))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		return true;
	}

	@Override
	public int compareTo(DespesaVisitaCliente o) {
		return this.getOrdem().compareTo(o.getOrdem());
	}
}