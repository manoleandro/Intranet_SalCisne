package br.com.sp.intranet.model.administrador.vo.rh;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.primefaces.model.StreamedContent;

import br.com.sp.intranet.controller.administrador.TipoFuncionarioEnum;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.vo.ornanograma.Responsavel;

@Entity
@Table(name = "COLABORADOR", uniqueConstraints = {})
public class Colaborador  extends GenericEntity implements Comparable<Colaborador>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long matricula;
	private String nome;
	private Cargo cargo;
	private Diretoria diretoria;
	private Departamento departamento;
	private Setor setor;
	private Secao secao;
	private Date admissao;
	private Date demissao;
	private String status;
	private Filial filial;
	private Filial filialCargo;
	private Filial filialDiretoria;
	private Filial filialDepartamento;
	private Filial filialSetor;
	private Filial filialSecao;
	private Date aniversario;
	private TipoFuncionarioEnum tipoFuncionario;
	private String uf;
	private boolean isDiretor;
	private Responsavel responsavelDiretor;
	private Set<Ferias> feriases = new HashSet<Ferias>(0);
	private Set<HistoricoAfastamento> historicoAfastamentos = new HashSet<HistoricoAfastamento>(0);
	private Set<HistoricoSalario> historicoSalarios = new HashSet<HistoricoSalario>(0);
	private boolean geraResultado;
	private StreamedContent foto;

	@Id
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MATRICULA", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	@Column(name = "NOME", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns( {
		@JoinColumn(name = "FILIAL_DEPARTAMENTO", referencedColumnName = "COD_FILIAL", insertable=true,updatable= true,nullable=true),
        @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable=true,updatable= true,nullable=true)})
	@NotFound(action = NotFoundAction.IGNORE)
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns( {
		@JoinColumn(name = "FILIAL_DIRETORIA", referencedColumnName = "ID_FILIAL",insertable=true,updatable= true,nullable=true),
		@JoinColumn(name = "ID_DIRETORIA", referencedColumnName = "ID", insertable=true,updatable= true,nullable=true)})
	@NotFound(action = NotFoundAction.IGNORE)
	public Diretoria getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(Diretoria diretoria) {
		this.diretoria = diretoria;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns( {
		@JoinColumn(name = "FILIAL_SETOR", referencedColumnName = "ID_FILIAL",insertable=true,updatable= true,nullable=true),
        @JoinColumn(name = "ID_SETOR", referencedColumnName = "ID", insertable=true,updatable= true,nullable=true)})
	@NotFound(action = NotFoundAction.IGNORE)
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns( {
		@JoinColumn(name = "FILIAL_SECAO", referencedColumnName = "ID_FILIAL",insertable=true,updatable= true,nullable=true),
        @JoinColumn(name = "ID_SECAO", referencedColumnName = "ID", insertable=true,updatable= true,nullable=true)})
	@NotFound(action = NotFoundAction.IGNORE)
	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns( {
		@JoinColumn(name = "FILIAL_CARGO", referencedColumnName = "COD_FILIAL",insertable=true,updatable= true,nullable=true),
		@JoinColumn(name = "ID_CARGO", referencedColumnName = "ID_CARGO", insertable=true,updatable= true,nullable=true)})
	@NotFound(action = NotFoundAction.IGNORE)
	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADMISSAO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getAdmissao() {
		return this.admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEMISSAO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getDemissao() {
		return this.demissao;
	}

	public void setDemissao(Date demissao) {
		this.demissao = demissao;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "colaborador")
	public Set<Ferias> getFeriases() {
		return this.feriases;
	}

	public void setFeriases(Set<Ferias> feriases) {
		this.feriases = feriases;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "colaborador")
	public Set<HistoricoAfastamento> getHistoricoAfastamentos() {
		return this.historicoAfastamentos;
	}

	public void setHistoricoAfastamentos(Set<HistoricoAfastamento> historicoAfastamentos) {
		this.historicoAfastamentos = historicoAfastamentos;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "colaborador")
	public Set<HistoricoSalario> getHistoricoSalarios() {
		return this.historicoSalarios;
	}

	public void setHistoricoSalarios(Set<HistoricoSalario> historicoSalarios) {
		this.historicoSalarios = historicoSalarios;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FILIAL", nullable= true)
	public Filial getFilial() {
		return this.filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@Column(name = "ANIVERSARIO")
	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_FUNCIONARIO")
	public TipoFuncionarioEnum getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionarioEnum tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	@Transient
	public String getUf() {
		if (getFilial().getId() == 240) {
			return "SP";
		} else {
			return "CF";
		}
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getId();
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FILIAL_CARGO", nullable= true, updatable= false, insertable= false)
	public Filial getFilialCargo() {
		return filialCargo;
	}

	public void setFilialCargo(Filial filialCargo) {
		this.filialCargo = filialCargo;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FILIAL_DIRETORIA", nullable= true, updatable= false, insertable= false)
	public Filial getFilialDiretoria() {
		return filialDiretoria;
	}

	public void setFilialDiretoria(Filial filialDiretoria) {
		this.filialDiretoria = filialDiretoria;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FILIAL_DEPARTAMENTO", nullable= true, updatable= false, insertable= false)
	public Filial getFilialDepartamento() {
		return filialDepartamento;
	}

	public void setFilialDepartamento(Filial filialDepartamento) {
		this.filialDepartamento = filialDepartamento;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FILIAL_SETOR", nullable= true, updatable= false, insertable= false)
	public Filial getFilialSetor() {
		return filialSetor;
	}

	public void setFilialSetor(Filial filialSetor) {
		this.filialSetor = filialSetor;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FILIAL_SECAO", nullable= true, updatable= false, insertable= false)
	public Filial getFilialSecao() {
		return filialSecao;
	}

	public void setFilialSecao(Filial filialSecao) {
		this.filialSecao = filialSecao;
	}
	
	
	
	
	@Transient
	public String getStatus() {
		if(this.demissao == null) {
			setStatus("A");
		} else {
			setStatus("D");
		}
		
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public boolean isDiretor() {
		return isDiretor;
	}

	public void setDiretor(boolean isDiretor) {
		this.isDiretor = isDiretor;
	}
	
	@Transient
	public Responsavel getResponsavelDiretor() {
		return responsavelDiretor;
	}

	public void setResponsavelDiretor(Responsavel responsavelDiretor) {
		this.responsavelDiretor = responsavelDiretor;
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
	public StreamedContent getFoto() {
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
	
	
	
	/*public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	} */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Colaborador)) {
			return false;
		}
		Colaborador other = (Colaborador) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Colaborador o) {
		return this.getNome().compareTo(o.getNome());
	}

}