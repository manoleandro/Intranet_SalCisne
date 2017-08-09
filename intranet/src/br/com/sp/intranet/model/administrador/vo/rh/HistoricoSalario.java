package br.com.sp.intranet.model.administrador.vo.rh;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * HistoricoSalario entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HISTORICO_SALARIO", uniqueConstraints = {})
@SequenceGenerator(name="SEQ_HISTORICO_SALARIO",sequenceName="SEQ_HISTORICO_SALARIO")
public class HistoricoSalario extends GenericEntity {

	// Fields
	private Long idHistSalario;
	private Colaborador colaborador;
	private Date dataAumento;
	private String salario;
	private String percentual;
	private Double salarioD;
	private MotivoHistoricoSalarioEnum motivo;

	// Constructors

	/** default constructor */
	public HistoricoSalario() {
	}

	/** minimal constructor */
	public HistoricoSalario(Long idHistSalario) {
		this.idHistSalario = idHistSalario;
	}

	/** full constructor */
	public HistoricoSalario(Long idHistSalario, Colaborador colaborador,
			Date dataAumentoColetivo, Date dataAumentoIndividual,
			String salario, Long versao, String usuarioUltAlteracao,
			Date dataUltAlteracao,
			String percentual, MotivoHistoricoSalarioEnum motivo) {
		this.idHistSalario = idHistSalario;
		this.colaborador = colaborador;
		this.dataAumento = dataAumento;
		this.salario = salario;
		this.versao = versao;
		this.usuarioUltAlteracao = usuarioUltAlteracao;
		this.dataUltAlteracao = dataUltAlteracao;
		this.percentual = percentual;
		this.motivo = motivo;
	}

	// Property accessors
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HISTORICO_SALARIO")
	@Column(name = "ID_HIST_SALARIO", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getIdHistSalario() {
		return this.idHistSalario;
	}

	public void setIdHistSalario(Long idHistSalario) {
		this.idHistSalario = idHistSalario;
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
	@Column(name = "DATA_AUMENTO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getDataAumento() {
		return dataAumento;
	}

	public void setDataAumento(Date dataAumento) {
		this.dataAumento = dataAumento;
	}
	
	@Column(name = "SALARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getSalario() {
		return this.salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}
	
	@Column(name="PERCENTUAL")
	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="MOTIVO")
	public MotivoHistoricoSalarioEnum getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoHistoricoSalarioEnum motivo) {
		this.motivo = motivo;
	}

	@Transient
	public Double getSalarioD() {
		return salarioD;
	}

	public void setSalarioD(Double salarioD) {
		this.salarioD = salarioD;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdHistSalario();
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