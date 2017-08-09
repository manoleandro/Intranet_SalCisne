package br.com.sp.intranet.model.administrador;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.util.DataTypes;

@Entity
@Table(name = "CS_MENU", uniqueConstraints = {})
@SequenceGenerator(name="SE_CS_MENU",sequenceName="SE_CS_MENU")
public class CsMenu extends GenericEntity implements Comparable<CsMenu>{

	private Long id;
	private String descricao;
	private Set<CsServico> csServicos = new HashSet<CsServico>(0);
	private Integer ordem;
	private String icone;
	private CsMenu superior;

	public CsMenu() {}

	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SE_CS_MENU")
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

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<CsServico> getCsServicos() {
		return this.csServicos;
	}

	public void setCsServicos(Set<CsServico> csServicos) {
		this.csServicos = csServicos;
	}
	
	@Column(name="ORDEM")
	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
	@Column(name = "ICONE")
	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	@OneToOne
	@JoinColumn(name = "SUPERIOR")
	public CsMenu getSuperior() {
		return superior;
	}

	public void setSuperior(CsMenu superior) {
		this.superior = superior;
	}
	
	@Override
	@Transient
	public Object getPrimaryKey() {
		return getId();
	}

	@Override
	public int compareTo(CsMenu o) {
		Integer ordem = DataTypes.parseNull(this.getOrdem());
		
		if(ordem.compareTo(o.getOrdem()) != 0){
			return ordem.compareTo(o.getOrdem());
		}else{
			String descricao = DataTypes.parseNull(this.getDescricao());
			return descricao.compareTo(o.getDescricao());
		}
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
		CsMenu other = (CsMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}