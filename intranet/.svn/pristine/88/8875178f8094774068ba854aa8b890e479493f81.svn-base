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
 * HistoricoAfastamento entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HISTORICO_AFASTAMENTO", uniqueConstraints = {})
@SequenceGenerator(name = "SEQ_HISTORICO_AFASTAMENTO", sequenceName = "SEQ_HISTORICO_AFASTAMENTO")
public class HistoricoAfastamento extends GenericEntity implements Comparable<HistoricoAfastamento>{

	// Fields

	private Long idHistAfastamento;
	private Colaborador colaborador;
	private Date dataAfastamento;
	private Date dataRetorno;
	private TipoAfastamento tipoAfastamento;

	// Constructors

	/** default constructor */
	public HistoricoAfastamento() {
	}

	/** minimal constructor */
	public HistoricoAfastamento(Long idHistAfastamento) {
		this.idHistAfastamento = idHistAfastamento;
	}

	/** full constructor */
	public HistoricoAfastamento(Long idHistAfastamento, Colaborador colaborador, Date dataAfastamento, Date dataRetorno,
		Long versao, String usuarioUltAlteracao, Date dataUltAlteracao) {
		this.idHistAfastamento = idHistAfastamento;
		this.colaborador = colaborador;
		this.dataAfastamento = dataAfastamento;
		this.dataRetorno = dataRetorno;
		this.versao = versao;
		this.usuarioUltAlteracao = usuarioUltAlteracao;
		this.dataUltAlteracao = dataUltAlteracao;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HISTORICO_AFASTAMENTO")
	@Column(name = "ID_HIST_AFASTAMENTO", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdHistAfastamento() {
		return this.idHistAfastamento;
	}

	public void setIdHistAfastamento(Long idHistAfastamento) {
		this.idHistAfastamento = idHistAfastamento;
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
	@Column(name = "DATA_AFASTAMENTO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getDataAfastamento() {
		return this.dataAfastamento;
	}

	public void setDataAfastamento(Date dataAfastamento) {
		this.dataAfastamento = dataAfastamento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_RETORNO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getDataRetorno() {
		return this.dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_AFASTAMENTO", unique = false, nullable = true, insertable = true, updatable = true)
	public TipoAfastamento getTipoAfastamento() {
		return tipoAfastamento;
	}

	public void setTipoAfastamento(TipoAfastamento tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdHistAfastamento();
	}

	@Override
	public int compareTo(HistoricoAfastamento o) {
		return this.getIdHistAfastamento().compareTo(o.getIdHistAfastamento());
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
