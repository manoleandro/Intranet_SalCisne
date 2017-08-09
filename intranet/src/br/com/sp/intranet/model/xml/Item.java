package br.com.sp.intranet.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", propOrder = { "title", "date", "user", "link", "target", "ramal", "setor", "uf", "logradouro",
		"numero", "cep", "description" })
public class Item {

	@XmlElement(required = true)
	protected String date;
	@XmlElement(required = true)
	protected String user;
	@XmlElement(required = true)
	protected String title;
	@XmlElement(required = true)
	protected String link;
	@XmlElement(required = true)
	protected String target;
	@XmlElement(required = true)
	protected String ramal;
	@XmlElement(required = true)
	protected String setor;
	@XmlElement(required = true)
	protected String uf;
	@XmlElement(required = true)
	protected String description;
	@XmlElement(required = true)
	protected String logradouro;
	@XmlElement(required = true)
	protected String numero;
	@XmlElement(required = true)
	protected String cep;
	@XmlTransient
	protected String marcacao;
	@XmlTransient
	protected String aviso;
	@XmlTransient
	protected String outroSetor;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public String getMarcacao() {
		return marcacao;
	}

	public void setMarcacao(String marcacao) {
		this.marcacao = marcacao;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getOutroSetor() {
		return outroSetor;
	}

	public void setOutroSetor(String outroSetor) {
		this.outroSetor = outroSetor;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
