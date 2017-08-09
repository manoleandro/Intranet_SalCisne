package br.com.sp.intranet.model.comercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WSR_VENDEDOR")
public class Vendedor implements Comparable<Vendedor>{
	
	private Long idVendedor;
	private Long codZonaVendas;
	private String nomeVendedor;
	
	public Vendedor() {}

	public Vendedor(VendedorClienteByMunicipio vendedor) {
		this.idVendedor = vendedor.getIdVendedor();
		this.codZonaVendas = vendedor.getCodZonaVendas();
		this.nomeVendedor = vendedor.getNomeVendedor();
	}

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Vendedor other = (Vendedor) obj;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		return true;
	}

	@Override
	public int compareTo(Vendedor o) {
		return this.getNomeVendedor().compareTo(o.getNomeVendedor());
	}
}