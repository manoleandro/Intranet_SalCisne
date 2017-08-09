package br.com.sp.intranet.model.portaria;

import javax.persistence.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "HIST_MOV_PORTARIA")
@SequenceGenerator(name="SEQ_HIST_MOV_PORTARIA",sequenceName="SEQ_HIST_MOV_PORTARIA")
public class HistMovPortaria extends GenericEntity {
	
	private Long id;
	private String coluna;
	private String de;
	private String para;
	private Date data;
	private String usuario;
	private String justificativa;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_HIST_MOV_PORTARIA")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "COLUNA")
	public String getColuna() {
		return coluna;
	}
	
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	
	@Column(name = "DE")
	public String getDe() {
		return de;
	}
	
	public void setDe(String de) {
		this.de = de;
	}
	
	@Column(name = "PARA")
	public String getPara() {
		return para;
	}
	
	public void setPara(String para) {
		this.para = para;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Column(name = "USUARIO")
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "JUSTIFICATIVA")
	public String getJustificativa() {
		return justificativa;
	}
	
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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