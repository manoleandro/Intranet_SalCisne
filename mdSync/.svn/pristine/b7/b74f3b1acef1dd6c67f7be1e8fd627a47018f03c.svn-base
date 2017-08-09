package com.mdSync.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CS_SERVICO_APP")
@SequenceGenerator(name="SEQ_SERVICO_APP",sequenceName="SEQ_SERVICO_APP")
public class CsServicoApp{
	
	private Long id;
	private String descricao;
	private String regra;
	private String aplicativo;
	private TipoPermissaoEnum tipoPermissao;
	private String mapeamento;
	
	public CsServicoApp(){}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_SERVICO_APP")
	@Column(name ="ID")
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PERMISSAO")
	public TipoPermissaoEnum getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(TipoPermissaoEnum tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}

	@Column(name = "MAPEAMENTO")
	public String getMapeamento() {
		return mapeamento;
	}

	public void setMapeamento(String mapeamento) {
		this.mapeamento = mapeamento;
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
		CsServicoApp other = (CsServicoApp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}