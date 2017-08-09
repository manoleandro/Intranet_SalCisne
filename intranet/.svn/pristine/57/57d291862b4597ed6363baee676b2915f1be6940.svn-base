package br.com.sp.intranet.model.administrador.vo.rh;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DepartamentoPK implements Serializable{
	
	private Long id;
	private Filial filial;
	
	@Column(name="ID_DEPARTAMENTO")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={})
	@JoinColumn(name = "COD_FILIAL", unique = false, nullable = true, insertable = false, updatable = false)
	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DepartamentoPK)) {
			return false;
		}
		DepartamentoPK other = (DepartamentoPK) obj;
		if (filial == null) {
			if (other.filial != null) {
				return false;
			}
		} else if (!filial.equals(other.filial)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}