package br.com.sp.intranet.model.administrador;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

/**
 * CsSetor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CS_SETOR", uniqueConstraints = {})
@SequenceGenerator(name="SE_CS_SETOR",sequenceName="SE_CS_SETOR")
public class CsSetor extends GenericEntity {

	// Fields

	private Long idSetor;
	private String codSetor;
	private String descricao;
	private String email;

	// Constructors

	/** default constructor */
	public CsSetor() {
	}

	/** minimal constructor */
	public CsSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	/** full constructor */
	public CsSetor(Long idSetor, Long versao, Date dataUltAlteracao,
			String usuarioUltAlteracao, String codSetor, String descricao) {
		this.idSetor = idSetor;
		this.codSetor = codSetor;
		this.descricao = descricao;
	}

	// Property accessors
	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SE_CS_SETOR")
	@Column(name = "ID_SETOR", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdSetor() {
		return this.idSetor;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	@Column(name = "COD_SETOR", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public String getCodSetor() {
		return this.codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "EMAIL", unique = false, nullable = true, insertable = true, updatable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return getIdSetor();
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