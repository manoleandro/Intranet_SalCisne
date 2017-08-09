package wsr.model.comercial;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "WSR_VENDEDOR")
public class VendedorClienteByMunicipio implements Serializable{
	
	private Long idVendedor;
	private Long codZonaVendas;
	private String nomeVendedor;
	private String mesAno;
	private List<Municipio> municipios;
	
	public VendedorClienteByMunicipio() {}

	@Id
	@Column(name = "ID_VENDEDOR")
	public Long getIdVendedor() {
		return idVendedor;
	}
	
	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	@Column(name = "COD_ZONA_VENDAS")
	public Long getCodZonaVendas() {
		return codZonaVendas;
	}
	
	public void setCodZonaVendas(Long codZonaVendas) {
		this.codZonaVendas = codZonaVendas;
	}
	
	@Column(name = "NOME")
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_VENDEDOR")
	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		Collections.sort(municipios);
		this.municipios = municipios;
	}
	
	@Transient
	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codZonaVendas == null) ? 0 : codZonaVendas.hashCode());
		result = prime * result + ((idVendedor == null) ? 0 : idVendedor.hashCode());
		result = prime * result + ((mesAno == null) ? 0 : mesAno.hashCode());
		result = prime * result + ((municipios == null) ? 0 : municipios.hashCode());
		result = prime * result + ((nomeVendedor == null) ? 0 : nomeVendedor.hashCode());
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
		VendedorClienteByMunicipio other = (VendedorClienteByMunicipio) obj;
		if (codZonaVendas == null) {
			if (other.codZonaVendas != null)
				return false;
		} else if (!codZonaVendas.equals(other.codZonaVendas))
			return false;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		if (mesAno == null) {
			if (other.mesAno != null)
				return false;
		} else if (!mesAno.equals(other.mesAno))
			return false;
		if (municipios == null) {
			if (other.municipios != null)
				return false;
		} else if (!municipios.equals(other.municipios))
			return false;
		if (nomeVendedor == null) {
			if (other.nomeVendedor != null)
				return false;
		} else if (!nomeVendedor.equals(other.nomeVendedor))
			return false;
		return true;
	}
}