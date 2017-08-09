package br.com.sp.intranet.model.administrador.vo.rh;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;


@Entity
@Table(name = "DEPARTAMENTO", uniqueConstraints = {})
public class Departamento extends GenericEntity {
	
	private DepartamentoPK pk;
	private String descricao;
	
	public Departamento(){}
	
	public Departamento(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	@EmbeddedId
	public DepartamentoPK getPk() {
		return pk;
	}

	public void setPk(DepartamentoPK pk) {
		this.pk = pk;
	}

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	@Override
	@Transient
	public Object getPrimaryKey() {
		return getPk();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		if (!(obj instanceof Departamento)) {
			return false;
		}
		Departamento other = (Departamento) obj;
		if (pk == null) {
			if (other.pk != null) {
				return false;
			}
		} else if (!pk.equals(other.pk)) {
			return false;
		}
		return true;
	}
}