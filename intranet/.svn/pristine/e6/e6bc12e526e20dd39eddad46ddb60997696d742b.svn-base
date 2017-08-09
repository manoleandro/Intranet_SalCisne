package br.com.sp.intranet.model.administrador.vo.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;



@SuppressWarnings("serial")
@Entity
@Table(name = "FILIAL", uniqueConstraints = {})
public class Filial extends GenericEntity {

	private Long id;
	private String descricao;

	public Filial() {

	}

	public Filial(Long idFilial, String descricao) {
		this.id = idFilial;
		this.descricao = descricao;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		return getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		if (!(obj instanceof Filial)) {
			return false;
		}
		Filial other = (Filial) obj;
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
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