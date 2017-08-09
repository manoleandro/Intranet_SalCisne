package com.mdSync.model.comercial;

import java.io.Serializable;
import java.util.List;

public class VendedorClienteByMunicipio implements Serializable{
	
	private Long idVendedor;
	private Long codZonaVendas;
	private String nomeVendedor;
	private String mesAno;
	
	private List<Municipio> municipios;
	
	public VendedorClienteByMunicipio() {
		super();
	}

	public Long getIdVendedor() {
		return idVendedor;
	}
	
	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	public Long getCodZonaVendas() {
		return codZonaVendas;
	}
	
	public void setCodZonaVendas(Long codZonaVendas) {
		this.codZonaVendas = codZonaVendas;
	}
	
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	
	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVendedor == null) ? 0 : idVendedor.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendedorClienteByMunicipio other = (VendedorClienteByMunicipio) obj;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		return true;
	}
}