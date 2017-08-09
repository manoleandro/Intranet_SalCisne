package br.com.sp.intranet.model.administrador.vo.ornanograma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import br.com.sp.intranet.controller.administrador.TipoAreaEnum;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.vo.rh.Filial;



@Entity
@Table(name = "ORGANOGRAMA", uniqueConstraints = {})
@SequenceGenerator(name = "SEQ_ORGANOGRAMA", sequenceName = "SEQ_ORGANOGRAMA")
public class Organograma extends GenericEntity {

	private Long id;
	private String descricao;
	private Long superior;
	private String descricaoSuperior;
	private Filial filial;
	private TipoAreaEnum tipo;
	private Long idArea;
	private String descricaoArea;
	private boolean geraResultado;

	public Organograma() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ORGANOGRAMA")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 19)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "SUPERIOR", unique = true, nullable = true, insertable = true, updatable = true, length = 20)
	public Long getSuperior() {
		return superior;
	}

	public void setSuperior(Long superior) {
		this.superior = superior;
	}

	@Column(name = "DESCRICAO_SUP")
	public String getDescricaoSuperior() {
		return descricaoSuperior;
	}

	public void setDescricaoSuperior(String descricaoSuperior) {
		this.descricaoSuperior = descricaoSuperior;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_AREA")
	public TipoAreaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoAreaEnum tipo) {
		this.tipo = tipo;
	}

	@Column(name = "ID_AREA")
	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	@Column(name = "DESCRICAO_AREA")
	public String getDescricaoArea() {
		return descricaoArea;
	}

	public void setDescricaoArea(String descricaoArea) {
		this.descricaoArea = descricaoArea;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FILIAL")
	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	@Type(type = "true_false")
	@Column(name = "GERA_RESULTADO")
	public boolean isGeraResultado() {
		return geraResultado;
	}

	public void setGeraResultado(boolean geraResultado) {
		this.geraResultado = geraResultado;
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
