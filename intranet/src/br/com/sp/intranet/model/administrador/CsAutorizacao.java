package br.com.sp.intranet.model.administrador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "CS_AUTORIZACAO")
@SequenceGenerator(name="SEQ_CS_AUTORIZACAO",sequenceName="SEQ_CS_AUTORIZACAO")
public class CsAutorizacao extends GenericEntity implements Comparable<CsAutorizacao>{
	
	private static final long serialVersionUID = 1L; 
	
	private Long id;
	private CsServico servico;
	private String autorizacao;
	private String descricao;
	
	public CsAutorizacao(){}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_CS_AUTORIZACAO")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SERVICO")
	public CsServico getServico() {
		return servico;
	}
	
	public void setServico(CsServico servico) {
		this.servico = servico;
	}
	
	@Column(name = "AUTORIZACAO")
	public String getAutorizacao() {
		return autorizacao;
	}
	
	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
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
		CsAutorizacao other = (CsAutorizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(CsAutorizacao o) {
		return this.getId().compareTo(o.getId());
	}
}