package br.com.sp.intranet.model.caixa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "HIST_PADRAO")
@SequenceGenerator(name="SEQ_HIST_PADRAO",sequenceName="SEQ_HIST_PADRAO")
public class HistoricoPadrao extends GenericEntity{
	
	private Long id;
	private String descricao;
	
	public HistoricoPadrao(){
		
	}
		
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HIST_PADRAO")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 25, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRICAO", unique = true, nullable = false, insertable = true, updatable = true)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	@Transient
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
