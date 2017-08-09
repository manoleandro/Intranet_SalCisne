package wsr.model.comercial;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import wsr.converter.LocalDateConverter;

@Embeddable
public class RoteiroVisitaPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idCliente;
	private LocalDate dataVisita;
	
	@Column(name = "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name = "DATA_VISITA")
	@Convert(converter = LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	public LocalDate getDataVisita() {
		return dataVisita;
	}
	
	public void setDataVisita(LocalDate dataVisita) {
		this.dataVisita = dataVisita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataVisita == null) ? 0 : dataVisita.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		RoteiroVisitaPK other = (RoteiroVisitaPK) obj;
		if (dataVisita == null) {
			if (other.dataVisita != null)
				return false;
		} else if (!dataVisita.equals(other.dataVisita))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}
}