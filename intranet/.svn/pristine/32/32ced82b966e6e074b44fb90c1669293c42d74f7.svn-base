
package br.com.sp.intranet.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public abstract class GenericEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Long versao;
	protected Date dataUltAlteracao;
	protected String usuarioUltAlteracao;

	@Version
	@Column(name = "VERSAO", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ULT_ALTERACAO", unique = false, nullable = false, insertable = true, updatable = true)
	public Date getDataUltAlteracao() {
		return dataUltAlteracao;
	}

	public void setDataUltAlteracao(Date dataUltAlteracao) {
		this.dataUltAlteracao = dataUltAlteracao;
	}

	@Column(name = "USUARIO_ULT_ALTERACAO", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getUsuarioUltAlteracao() {
		return usuarioUltAlteracao;
	}

	public void setUsuarioUltAlteracao(String usuarioUltAlteracao) {
		this.usuarioUltAlteracao = usuarioUltAlteracao;
	}
	
	@Transient
	public abstract Object getPrimaryKey();
	
	public abstract int hashCode();

	public abstract boolean equals(Object obj);
}