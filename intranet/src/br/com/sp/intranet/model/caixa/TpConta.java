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
@Table(name = "TP_CONTA")
@SequenceGenerator(name="SEQ_TP_CONTA",sequenceName="SEQ_TP_CONTA")
public class TpConta extends GenericEntity{
	
	private Long id;
	private String descricao;
	
	public TpConta(){
		
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_CONTA")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRICAO")
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
