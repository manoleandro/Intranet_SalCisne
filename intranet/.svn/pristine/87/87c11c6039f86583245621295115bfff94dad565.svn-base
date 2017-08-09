package br.com.sp.intranet.model.portaria;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;


/**
 * Veiculo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VEICULO")
@SequenceGenerator(name="SEQ_VEICULO_PORTARIA", sequenceName="SEQ_VEICULO_PORTARIA")
public class Veiculo extends GenericEntity {

	// Fields
	private Long idVeiculo;
	private String descricao;
	private Set<MovPortaria> movPortarias = new HashSet<MovPortaria>(0);

	// Constructors

	/** default constructor */
	public Veiculo() {
	}

	/** minimal constructor */
	public Veiculo(Long idVeiculo, Long versao, Date dataUltAlteracao,
			String usuarioUltAlteracao, Long codVeiculo, String descricao) {
		this.idVeiculo = idVeiculo;
		this.descricao = descricao;
	}

	/** full constructor */
	public Veiculo(Long idVeiculo, Long versao, Date dataUltAlteracao,
			String usuarioUltAlteracao, Long codVeiculo, String descricao,
			Set<MovPortaria> movPortarias) {
		this.idVeiculo = idVeiculo;
		this.descricao = descricao;
		this.movPortarias = movPortarias;
	}

	// Property accessors
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_VEICULO_PORTARIA")
	@Column(name = "ID_VEICULO", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdVeiculo() {
		return this.idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "veiculo")
	public Set<MovPortaria> getMovPortarias() {
		return this.movPortarias;
	}

	public void setMovPortarias(Set<MovPortaria> movPortarias) {
		this.movPortarias = movPortarias;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return getIdVeiculo();
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