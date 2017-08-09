package br.com.sp.intranet.model.portaria;

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
@Table(name = "TRANSPORTADORA_PORTARIA")
@SequenceGenerator(name="SEQ_TRANSPORTADORA_PORTARIA", sequenceName="SEQ_TRANSPORTADORA_PORTARIA")
public class TransportadoraPortaria extends GenericEntity {

	// Fields
	private Long idTransportadora;
	private String descricao;

	// Constructors

	/** default constructor */
	public TransportadoraPortaria() {
	}

	/** minimal constructor */
	public TransportadoraPortaria(Long idTransportadora) {
		this.idTransportadora = idTransportadora;
	}

	/** full constructor */
	public TransportadoraPortaria(Long idTransportadora, String descricao) {
		this.idTransportadora = idTransportadora;
		this.descricao = descricao;
	}

	// Property accessors
	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_TRANSPORTADORA_PORTARIA")
	@Column(name = "ID_TRANSPORTADORA", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdTransportadora() {
		return this.idTransportadora;
	}

	public void setIdTransportadora(Long idTransportadora) {
		this.idTransportadora = idTransportadora;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Transient
	@Override
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return getIdTransportadora();
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