package wsr.model.comercial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MunicipioPK implements Serializable{
	
	private Long idVendedor;
	private Long idMunicipio;
	
	public MunicipioPK() {
		super();
	}
	
	@Column(name = "ID_VENDEDOR")
	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	@Column(name = "ID_MUNICIPIO")
	public Long getIdMunicipio() {
		return idMunicipio;
	}
	
	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMunicipio == null) ? 0 : idMunicipio.hashCode());
		result = prime * result + ((idVendedor == null) ? 0 : idVendedor.hashCode());
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
		MunicipioPK other = (MunicipioPK) obj;
		if (idMunicipio == null) {
			if (other.idMunicipio != null)
				return false;
		} else if (!idMunicipio.equals(other.idMunicipio))
			return false;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		return true;
	}
}