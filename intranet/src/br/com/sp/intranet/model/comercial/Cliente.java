package br.com.sp.intranet.model.comercial;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "WSR_CLIENTE")
public class Cliente implements Serializable{

	private Long codZonaVendas;
	private Long idUf;
	private String uf;
	private Long idMunicipio;
	private String nomeMunicipio;
	private Long idCliente;
	private String nomeFantasia;
	private String razao;
	private Long idVendedor;
	private String nomeVendedor;
	private Date dataVisita;
	
	public Cliente() {
		super();
	}

	@Id
	@Column(name = "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	@Column(name = "COD_ZONA_VENDAS")
	public Long getCodZonaVendas() {
		return codZonaVendas;
	}

	public void setCodZonaVendas(Long codZonaVendas) {
		this.codZonaVendas = codZonaVendas;
	}

	@Column(name = "SIGLA")
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "NOME_MUNICIPIO")
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	
	@Column(name = "NOME_FANTASIA")
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Column(name = "NOME")
	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	@Column(name = "ID_MUNICIPIO")
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	@Column(name = "ID_UF")
	public Long getIdUf() {
		return idUf;
	}

	public void setIdUf(Long idUf) {
		this.idUf = idUf;
	}

	@Column(name = "ID_VENDEDOR")
	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	@Column(name = "VENDEDOR")
	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	@Transient
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codZonaVendas == null) ? 0 : codZonaVendas.hashCode());
		result = prime * result + ((dataVisita == null) ? 0 : dataVisita.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((idMunicipio == null) ? 0 : idMunicipio.hashCode());
		result = prime * result + ((idUf == null) ? 0 : idUf.hashCode());
		result = prime * result + ((idVendedor == null) ? 0 : idVendedor.hashCode());
		result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + ((nomeMunicipio == null) ? 0 : nomeMunicipio.hashCode());
		result = prime * result + ((nomeVendedor == null) ? 0 : nomeVendedor.hashCode());
		result = prime * result + ((razao == null) ? 0 : razao.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codZonaVendas == null) {
			if (other.codZonaVendas != null)
				return false;
		} else if (!codZonaVendas.equals(other.codZonaVendas))
			return false;
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
		if (idMunicipio == null) {
			if (other.idMunicipio != null)
				return false;
		} else if (!idMunicipio.equals(other.idMunicipio))
			return false;
		if (idUf == null) {
			if (other.idUf != null)
				return false;
		} else if (!idUf.equals(other.idUf))
			return false;
		if (idVendedor == null) {
			if (other.idVendedor != null)
				return false;
		} else if (!idVendedor.equals(other.idVendedor))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (nomeMunicipio == null) {
			if (other.nomeMunicipio != null)
				return false;
		} else if (!nomeMunicipio.equals(other.nomeMunicipio))
			return false;
		if (nomeVendedor == null) {
			if (other.nomeVendedor != null)
				return false;
		} else if (!nomeVendedor.equals(other.nomeVendedor))
			return false;
		if (razao == null) {
			if (other.razao != null)
				return false;
		} else if (!razao.equals(other.razao))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
}