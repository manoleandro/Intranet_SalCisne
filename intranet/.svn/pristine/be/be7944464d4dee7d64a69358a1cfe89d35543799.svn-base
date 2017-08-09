package br.com.sp.intranet.model.externo;

public class FotoCardapio implements Comparable<FotoCardapio> {

	private String titulo;
	private String acompanhamento;
	private String guarnicao;
	private String salada;
	private String sobremesa;
	private String nomeFoto;
	private String diaSemana;
	private String diaNumero;
	private String user;
	private boolean pratoDoDia;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public String getGuarnicao() {
		return guarnicao;
	}

	public void setGuarnicao(String guarnicao) {
		this.guarnicao = guarnicao;
	}

	public String getSalada() {
		return salada;
	}

	public void setSalada(String salada) {
		this.salada = salada;
	}

	public String getSobremesa() {
		return sobremesa;
	}

	public void setSobremesa(String sobremesa) {
		this.sobremesa = sobremesa;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}

	

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getDiaNumero() {
		return diaNumero;
	}

	public void setDiaNumero(String diaNumero) {
		this.diaNumero = diaNumero;
	}


	public boolean isPratoDoDia() {
		return pratoDoDia;
	}

	public void setPratoDoDia(boolean pratoDoDia) {
		this.pratoDoDia = pratoDoDia;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int compareTo(FotoCardapio o) {
		return this.getDiaNumero().compareTo(o.getDiaNumero());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaNumero == null) ? 0 : diaNumero.hashCode());
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
		FotoCardapio other = (FotoCardapio) obj;
		if (diaNumero == null) {
			if (other.diaNumero != null)
				return false;
		} else if (!diaNumero.equals(other.diaNumero))
			return false;
		return true;
	}
	
	
	

}
