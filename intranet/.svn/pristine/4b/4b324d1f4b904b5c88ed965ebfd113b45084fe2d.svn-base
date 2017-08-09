package br.com.sp.intranet.model.administrador;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;

@Entity
@Table(name = "CS_GRUPO", uniqueConstraints = {})
@SequenceGenerator(name="SE_CS_GRUPO",sequenceName="SE_CS_GRUPO")
public class CsGrupo extends GenericEntity{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	private List<CsServico> servicos;
	private List<CsAutorizacao> autorizacoes;
	private List<CsServicoApp> servicosApp;
	private List<CsAutorizacaoApp> autorizacoesApp;
	
	public CsGrupo() {}

	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SE_CS_GRUPO")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_GRUPO_SERVICO", joinColumns = {
			@JoinColumn(name = "ID_GRUPO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_SERVICO", nullable = false, updatable = false) })
	public List<CsServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<CsServico> servicos) {
		this.servicos = servicos;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_GRUPO_AUTORIZACAO", joinColumns = {
			@JoinColumn(name = "ID_GRUPO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_AUTORIZACAO", nullable = false, updatable = false) })
	public List<CsAutorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<CsAutorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_GRUPO_SERVICO_APP", joinColumns = {
			@JoinColumn(name = "ID_GRUPO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_SERVICO", nullable = false, updatable = false) })
	public List<CsServicoApp> getServicosApp() {
		return servicosApp;
	}

	public void setServicosApp(List<CsServicoApp> servicosApp) {
		this.servicosApp = servicosApp;
	}

	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_GRUPO_AUTORIZACAO_APP", joinColumns = {
			@JoinColumn(name = "ID_GRUPO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_AUTORIZACAO", nullable = false, updatable = false) })
	public List<CsAutorizacaoApp> getAutorizacoesApp() {
		return autorizacoesApp;
	}

	public void setAutorizacoesApp(List<CsAutorizacaoApp> autorizacoesApp) {
		this.autorizacoesApp = autorizacoesApp;
	}

	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getId();
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsGrupo other = (CsGrupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}