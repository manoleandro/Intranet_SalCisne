package br.com.sp.intranet.model.administrador.vo.ornanograma;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;


@Entity
@Table(name = "RESPONSAVEL")
@SequenceGenerator(name = "SEQ_RESPONSAVEL", sequenceName = "SEQ_RESPONSAVEL")
public class Responsavel extends GenericEntity {

	private Long id;
	private Organograma organograma;
	private Colaborador colaborador;
	private Date inicio;
	private Date fim;

	public Responsavel() {
	}

	public Responsavel(Long id) {
		this.id = id;
	}

	public Responsavel(Long id, Organograma organograma, Colaborador colaborador, Date inicio, Date fim) {
		this.id = id;
		this.organograma = organograma;
		this.colaborador = colaborador;
		this.inicio = inicio;
		this.fim = fim;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_RESPONSAVEL")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {})
	@JoinColumn(name = "ORGANOGRAMA_ID")
	public Organograma getOrganograma() {
		return organograma;
	}

	public void setOrganograma(Organograma organograma) {
		this.organograma = organograma;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {})
	@JoinColumn(name = "COLABORADOR_ID")
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO")
	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIM")
	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		return getId();
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