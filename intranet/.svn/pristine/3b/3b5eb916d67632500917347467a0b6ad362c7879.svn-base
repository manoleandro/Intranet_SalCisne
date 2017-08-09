package br.com.sp.intranet.model.evento;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "EVENTO")
@SequenceGenerator(name = "SEQ_EVENTO", sequenceName = "SEQ_EVENTO")
public class Evento extends GenericEntity {

	// Fields

	private Long idEvento;
	private String descricao;
	private Date data;

	private Set<Foto> fotos = new HashSet<Foto>(0);

	public Evento() {}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EVENTO")
	@Column(name = "ID_EVENTO", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "evento")
	public Set<Foto> getFotos() {
		return this.fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdEvento();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEvento == null) ? 0 : idEvento.hashCode());
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
		Evento other = (Evento) obj;
		if (idEvento == null) {
			if (other.idEvento != null)
				return false;
		} else if (!idEvento.equals(other.idEvento))
			return false;
		return true;
	}
}