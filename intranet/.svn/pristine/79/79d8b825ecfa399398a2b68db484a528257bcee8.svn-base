package br.com.sp.intranet.model.administrador.vo.rh;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;



@Entity
@Table(name = "CARGO", uniqueConstraints = {})
public class Cargo extends GenericEntity {

	private CargoPK pk;
	private String descricao;
	
	@EmbeddedId
	public CargoPK getPk() {
		return pk;
	}

	public void setPk(CargoPK pk) {
		this.pk = pk;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}