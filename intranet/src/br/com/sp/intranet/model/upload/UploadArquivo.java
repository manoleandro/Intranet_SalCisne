package br.com.sp.intranet.model.upload;

//default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.ui.Model;

import br.com.sp.intranet.model.GenericEntity;



/**
* UploadArquivo entity.
* 
* @author MyEclipse Persistence Tools
*/
@Entity
@Table(name = "UPLOAD_ARQUIVO")
@SequenceGenerator(name="SEQ_UPLOAD_ARQUIVO",sequenceName="SEQ_UPLOAD_ARQUIVO")
public class UploadArquivo extends GenericEntity {

	// Fields

	private Long idArquivo;
	private UploadPasta uploadPasta;
	private String descricao;
	private Date dtInclusao;
	private byte[] arquivo;
	private String nomeArquivo;
	private String tipoArquivo;
	private String usuarioComp;
	private String setorComp;
	
	// Constructors
	/** default constructor */
	public UploadArquivo() {
	}

	/** minimal constructor */
	public UploadArquivo(Long idArquivo, UploadPasta uploadPasta,
			Date dtInclusao) {
		this.idArquivo = idArquivo;
		this.uploadPasta = uploadPasta;
		this.dtInclusao = dtInclusao;
	}

	/** full constructor */
	public UploadArquivo(Long idArquivo, UploadPasta uploadPasta,
			String descricao, Date dtInclusao, byte[] arquivo,
			String nomeArquivo, Long versao, Date dataUltAlteracao,
			String usuarioUltAlteracao) {
		this.idArquivo = idArquivo;
		this.uploadPasta = uploadPasta;
		this.descricao = descricao;
		this.dtInclusao = dtInclusao;
		this.arquivo = arquivo;
		this.nomeArquivo = nomeArquivo;
		this.versao = versao;
		this.dataUltAlteracao = dataUltAlteracao;
		this.usuarioUltAlteracao = usuarioUltAlteracao;
	}

	// Property accessors
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_UPLOAD_ARQUIVO")
	@Column(name = "ID_ARQUIVO", unique = true, nullable = false, insertable = true, updatable = true, precision = 25, scale = 0)
	public Long getIdArquivo() {
		return this.idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PASTA", unique = false, nullable = true, insertable = true, updatable = true)
	public UploadPasta getUploadPasta() {
		return this.uploadPasta;
	}

	public void setUploadPasta(UploadPasta uploadPasta) {
		this.uploadPasta = uploadPasta;
	}
	
	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INCLUSAO", unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Date getDtInclusao() {
		return this.dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}
	
	@Lob
	@Column(name = "ARQUIVO", unique = false, nullable = true, insertable = true, updatable = true)
	public byte[] getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	@Column(name = "NOME_ARQUIVO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	
	@Column(name = "TIPO_ARQUIVO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	@Column(name = "USUARIO_COMP", unique = false, nullable = true, insertable = true, updatable = true)
	public String getUsuarioComp() {
		return usuarioComp;
	}
	
	
	public void setUsuarioComp(String usuarioComp) {
		this.usuarioComp = usuarioComp;
	}
	
	@Column(name = "SETOR_COMP", unique = false, nullable = true, insertable = true, updatable = true)
	public String getSetorComp() {
		return setorComp;
	}

	public void setSetorComp(String setorComp) {
		this.setorComp = setorComp;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdArquivo();
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
