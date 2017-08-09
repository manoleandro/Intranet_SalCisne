package wsr.model.comercial;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WSR_VENDEDOR")
public class VendedorCliente implements Serializable{
	
	private Long idVendedor;
	private Long codZonaVendas;
	private String nomeVendedor;
	private List<Cliente> clientes;
	
	public VendedorCliente() {
		super();
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
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_VENDEDOR")
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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
		VendedorCliente other = (VendedorCliente) obj;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		return true;
	}
}