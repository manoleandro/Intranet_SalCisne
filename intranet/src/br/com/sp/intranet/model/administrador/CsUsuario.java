package br.com.sp.intranet.model.administrador;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import br.com.sp.intranet.converter.LocalDateTimeConverter;
import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "CS_USUARIO", uniqueConstraints = {})
public class CsUsuario extends GenericEntity  {
	private static final long serialVersionUID = 1L;
 
	private String username;
	private String password;
	private boolean enable;
	private Long idSetor;
	private Long idConta;
	private Long idColaborador;
	private String nome;
	private String email;
	private boolean gestor;
	private List<CsGrupo> grupos;
	private List<CsServico> servicos;
	private List<CsAutorizacao> autorizacoes;
	private LocalDateTime dataUltimoAcesso;
	private String senha;
	private Long zonaVendas;
	
	public CsUsuario() {}

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, insertable = true, updatable = true, length = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Type(type = "true_false")
	@Column
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@Column(name = "ID_SETOR", unique = false, nullable = true, insertable = true, updatable = true, scale = 0)
	public Long getIdSetor() {
		return this.idSetor;
	}
	
	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	@Column(name = "ID_COLABORADOR", unique = false, nullable = true, insertable = true, updatable = true, scale = 0)
	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	@Column(name = "NOME", unique = false, nullable = true, insertable = true, updatable = true)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "EMAIL", unique = false, nullable = true, insertable = true, updatable = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Type(type = "true_false")
	@Column(name = "GESTOR")
	public boolean isGestor() {
		return gestor;
	}

	public void setGestor(boolean gestor) {
		this.gestor = gestor;
	}	
	
	@Column(name = "ID_CONTA")
	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_USUARIO_GRUPO", joinColumns = {
			@JoinColumn(name = "USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_GRUPO", nullable = false, updatable = false) })
	public List<CsGrupo> getGrupos() {
		return grupos;
	}
	
	public void setGrupos(List<CsGrupo> grupos) {
		this.grupos = grupos;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_USUARIO_SERVICO", joinColumns = {
			@JoinColumn(name = "USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_SERVICO", nullable = false, updatable = false) })
	public List<CsServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<CsServico> servicos) {
		this.servicos = servicos;
	}

	
	@Column(name = "DATA_ULT_ACESSO")
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(LocalDateTime dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CS_USUARIO_AUTORIZACAO", joinColumns = {
			@JoinColumn(name = "USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_AUTORIZACAO", nullable = false, updatable = false) })
	public List<CsAutorizacao> getAutorizacoes() {
		return autorizacoes;
	}
	
	public void setAutorizacoes(List<CsAutorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}
	
	@Column(name = "ZONA_VENDAS")
	public Long getZonaVendas() {
		return zonaVendas;
	}

	public void setZonaVendas(Long zonaVendas) {
		this.zonaVendas = zonaVendas;
	}
	
	@Transient
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		return getUsername();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		CsUsuario other = (CsUsuario) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}