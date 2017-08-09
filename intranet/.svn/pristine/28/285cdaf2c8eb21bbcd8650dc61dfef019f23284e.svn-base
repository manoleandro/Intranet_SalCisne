package br.com.sp.intranet.model.administrador.vo.rh;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;


@SuppressWarnings("serial")
@Entity
@Table(name = "SECAO", uniqueConstraints = {})
public class Secao extends GenericEntity {

	private SecaoPK pk;
	private String descricao;

	public Secao() {}
	
	public Secao(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	@EmbeddedId
	public SecaoPK getPk() {
		return pk;
	}
	
	public void setPk(SecaoPK pk) {
		this.pk = pk;
	}

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		return getPk();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		if (!(obj instanceof Secao)) {
			return false;
		}
		Secao other = (Secao) obj;
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
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