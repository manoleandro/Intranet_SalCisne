package wsr.model.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.WhereJoinTable;

import wsr.model.GenericEntity;

@Entity
@Table(name = "CS_USUARIO" )
public class CsUsuario extends GenericEntity{
	private String username;
	private String password;
	private boolean enable;
	private String nome;
	private String email;
	private Set<CsGrupo> grupos;
	private Long zonaVendas;

	public CsUsuario() {}

	@Id
	@Column(name = "USERNAME")
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

	@Column(name = "NOME")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CS_USUARIO_GRUPO", joinColumns = {
			@JoinColumn(name = "USUARIO", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_GRUPO", nullable = false, updatable = false) })
	@WhereJoinTable(clause = "ID_GRUPO IN (SELECT DISTINCT GSA.ID_GRUPO FROM CS_GRUPO_SERVICO_APP GSA)")
	public Set<CsGrupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<CsGrupo> grupos) {
		this.grupos = grupos;
	}
	
	@Column(name="ZONA_VENDAS")
	public Long getZonaVendas() {
		return zonaVendas;
	}

	public void setZonaVendas(Long zonaVendas) {
		this.zonaVendas = zonaVendas;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getUsername();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + ((grupos == null) ? 0 : grupos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enable != other.enable)
			return false;
		if (grupos == null) {
			if (other.grupos != null)
				return false;
		} else if (!grupos.equals(other.grupos))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}	
}