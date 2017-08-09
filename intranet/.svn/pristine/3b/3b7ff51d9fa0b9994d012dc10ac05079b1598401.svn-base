package br.com.sp.intranet.model.upload;


import java.util.Date; 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.engine.internal.Cascade;

import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsSetor;


/**
 * UploadPasta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UPLOAD_PASTA")
@SequenceGenerator(name="SEQ_UPLOAD_PASTA",sequenceName="SEQ_UPLOAD_PASTA")
public class UploadPasta extends GenericEntity {

	// Fields

	private Long idPasta;
	private CsSetor csSetor;
	private String descricao;
	private Long superior;
	
	// Constructors

	/** default constructor */
	public UploadPasta() {
	}

	/** minimal constructor */
	public UploadPasta(Long idPasta) {
		this.idPasta = idPasta;
	}

	/** full constructor */
	public UploadPasta(Long idPasta, CsSetor csSetor, String descricao,
			Long superior, Long versao, Date dataUltAlteracao,
			String usuarioUltAlteracao) {
		this.idPasta = idPasta;
		this.csSetor = csSetor;
		this.descricao = descricao;
		this.superior = superior;
		this.versao = versao;
		this.dataUltAlteracao = dataUltAlteracao;
		this.usuarioUltAlteracao = usuarioUltAlteracao;
		
	}

	// Property accessors
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_UPLOAD_PASTA")
	@Column(name = "ID_PASTA", unique = true, nullable = false, insertable = true, updatable = true, precision = 25, scale = 0)
	public Long getIdPasta() {
		return this.idPasta;
	}

	public void setIdPasta(Long idPasta) {
		this.idPasta = idPasta;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade={})
	@JoinColumn(name = "ID_SETOR", unique = false, nullable = true, insertable = true, updatable = true)
	public CsSetor getCsSetor() {
		return this.csSetor;
	}

	public void setCsSetor(CsSetor csSetor) {
		this.csSetor = csSetor;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "SUPERIOR", unique = false, nullable = true, insertable = true, updatable = true, precision = 25, scale = 0)
	public Long getSuperior() {
		return this.superior;
	}

	public void setSuperior(Long superior) {
		this.superior = superior;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdPasta();
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