package br.com.sp.intranet.model.administrador.app;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name ="CS_AUTORIZACAO_APP")
@SequenceGenerator(name="SEQ_AUTORIZACAO_APP",sequenceName="SEQ_AUTORIZACAO_APP")
public class CsAutorizacaoApp extends GenericEntity{
	
	private Long id;
	private String descricao;
	private String regra;
	private String aplicativo;
	
	public CsAutorizacaoApp(){}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_AUTORIZACAO_APP")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "REGRA")
	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}

	@Column(name = "APLICATIVO")
	public String getAplicativo() {
		return aplicativo;
	}

	public void setAplicativo(String aplicativo) {
		this.aplicativo = aplicativo;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CsAutorizacaoApp other = (CsAutorizacaoApp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}