package br.com.sp.intranet.model.administrador.vo.rh;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;


/**
 * Ferias entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FERIAS", uniqueConstraints = {})
@SequenceGenerator(name="SEQ_FERIAS",sequenceName="SEQ_FERIAS")
public class Ferias extends GenericEntity {

	// Fields

	private Long idFerias;
	private Colaborador colaborador;
	private Date inicio;
	private Date fim;
	private Date inicioPrdAquisitivo;
	private Date fimPrdAquisitivo;

	// Constructors

	/** default constructor */
	public Ferias() {
	}

	/** minimal constructor */
	public Ferias(Long idFerias) {
		this.idFerias = idFerias;
	}

	/** full constructor */
	public Ferias(Long idFerias, Colaborador colaborador, Date inicio,
			Date fim, Date inicioPrdAquisitivo, Date fimPrdAquisitivo,
			Long versao, String usuarioUltAlteracao, Date dataUltAlteracao) {
		this.idFerias = idFerias;
		this.colaborador = colaborador;
		this.inicio = inicio;
		this.fim = fim;
		this.inicioPrdAquisitivo = inicioPrdAquisitivo;
		this.fimPrdAquisitivo = fimPrdAquisitivo;
		this.versao = versao;
		this.usuarioUltAlteracao = usuarioUltAlteracao;
		this.dataUltAlteracao = dataUltAlteracao;
	}

	// Property accessors
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FERIAS")
	@Column(name = "ID_FERIAS", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdFerias() {
		return this.idFerias;
	}

	public void setIdFerias(Long idFerias) {
		this.idFerias = idFerias;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COLABORADOR", unique = false, nullable = true, insertable = true, updatable = true)
	public Colaborador getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIM", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getFim() {
		return this.fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO_PRD_AQUISITIVO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getInicioPrdAquisitivo() {
		return this.inicioPrdAquisitivo;
	}

	public void setInicioPrdAquisitivo(Date inicioPrdAquisitivo) {
		this.inicioPrdAquisitivo = inicioPrdAquisitivo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIM_PRD_AQUISITIVO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getFimPrdAquisitivo() {
		return this.fimPrdAquisitivo;
	}

	public void setFimPrdAquisitivo(Date fimPrdAquisitivo) {
		this.fimPrdAquisitivo = fimPrdAquisitivo;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return getIdFerias();
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