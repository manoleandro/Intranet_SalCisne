package wsr.model.comercial;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WSR_MUNICIPIO")
public class Municipio implements Serializable, Comparable<Municipio>{
	
	private MunicipioPK pk;
	private String descricao;
	private String uf;
	private List<Cliente> clientes;
	
	public Municipio() {
		super();
	}

	@EmbeddedId
	public MunicipioPK getPk() {
		return pk;
	}
	
	public void setPk(MunicipioPK pk) {
		this.pk = pk;
	}
	
	@Column(name = "NOME_MUNICIPIO")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID_VENDEDOR", insertable = false, updatable = false ),
		@JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO", insertable = false, updatable = false) })
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		Collections.sort(clientes);
		this.clientes = clientes;
	}
	
	@Column(name = "SIGLA")
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int compareTo(Municipio o) {
		return this.getDescricao().compareTo(o.getDescricao());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientes == null) ? 0 : clientes.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Municipio other = (Municipio) obj;
		if (clientes == null) {
			if (other.clientes != null)
				return false;
		} else if (!clientes.equals(other.clientes))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
}