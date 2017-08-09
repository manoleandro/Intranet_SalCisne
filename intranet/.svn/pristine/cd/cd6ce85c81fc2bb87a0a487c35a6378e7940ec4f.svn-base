package br.com.sp.intranet.model.administrador;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "CS_SERVICO", uniqueConstraints = {})
@SequenceGenerator(name="SEQ_CS_SERVICO",sequenceName="SEQ_CS_SERVICO")
public class CsServico extends GenericEntity implements Comparable<CsServico>{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private CsMenu menu;
	private String descricao;
	private String link;
	private boolean visivel;
	private Integer ordem;
	private String icone;
	private String regra;
	private String contexto;
	
	public CsServico() {}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_CS_SERVICO")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENU", unique = false, nullable = true, insertable = true, updatable = true)
	public CsMenu getMenu() {
		return menu;
	}

	public void setMenu(CsMenu menu) {
		this.menu = menu;
	}
	
	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getDescricao() {
		return this.descricao;
	}

	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "LINK", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Type(type = "true_false")
	@Column(name = "VISIVEL")
	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	
	@Column(name = "ORDEM")
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
	
	@Column(name = "REGRA")
	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}
	
	@Column(name = "CONTEXTO")
	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getId();
	}

	@Override
	public int compareTo(CsServico o) {
		if(this.getMenu() == null || this.getOrdem() == null || this.getDescricao() == null
				|| o.getMenu() == null || o.getOrdem() == null || o.getDescricao() == null)
			return -1;
			
		int ordemMenu = this.getMenu().getOrdem().compareTo(o.getMenu().getOrdem());
		if( ordemMenu == 0){
			int ordemDescricaoMenu = this.getMenu().getDescricao().compareTo(o.getMenu().getDescricao());
			
			if(ordemDescricaoMenu == 0) {
				int ordemServico = this.getOrdem().compareTo(o.getOrdem());
				
				if(ordemServico == 0){
					int ordemDescricaoServico = this.getDescricao().compareTo(o.getDescricao());
					return ordemDescricaoServico;
				}else{
					return ordemServico;
				}
			}else{
				return ordemDescricaoMenu;
			}			
		}else{
			return ordemMenu;
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
		CsServico other = (CsServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}