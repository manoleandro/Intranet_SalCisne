package wsr.model.comercial;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import wsr.converter.LocalDateConverter;
import wsr.model.GenericEntity;

@Entity
@Table(name ="HIST_DATA_VISITA")
@SequenceGenerator(name="SEQ_HIST_DATA_VISITA",sequenceName="SEQ_HIST_DATA_VISITA")
public class HistDataVisita extends GenericEntity{
	
	private Long id;
	private Long idCliente;
	private LocalDate dataVisita;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HIST_DATA_VISITA")
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name="DATA_VISITA")
	@Convert(converter = LocalDateConverter.class)
	public LocalDate getDataVisita() {
		return dataVisita;
	}
	
	public void setDataVisita(LocalDate dataVisita) {
		this.dataVisita = dataVisita;
	}
	
	@Transient
	@Override
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
		HistDataVisita other = (HistDataVisita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}