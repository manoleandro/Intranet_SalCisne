package br.com.sp.intranet.model.portaria;

import java.util.Date;

public class Tempos implements Comparable<Tempos>{
	
	private Date data;
	private String tempoCarregamento;
	private String tempoEspera;
	private String tempoMedCarregamentoPalet;
	private String tempoMedCarragamentoNPalet;
	private Long tempoEsperaMilis;
	private Long tempoCarregamentoMilis;
	private Long tempoMedCarregamentoPaletMilis;
	private Long tempoMedCarregamentoNPaletMilis;
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getTempoCarregamento() {
		return tempoCarregamento;
	}

	public void setTempoCarregamento(String tempoCarregamento) {
		this.tempoCarregamento = tempoCarregamento;
	}

	public String getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(String tempoEspera) {
		this.tempoEspera = tempoEspera;
	}
	
	public String getTempoMedCarregamentoPalet() {
		return tempoMedCarregamentoPalet;
	}
	
	public void setTempoMedCarregamentoPalet(String tempoMedCarregamentoPalet) {
		this.tempoMedCarregamentoPalet = tempoMedCarregamentoPalet;
	}
	
	public String getTempoMedCarragamentoNPalet() {
		return tempoMedCarragamentoNPalet;
	}
	
	public void setTempoMedCarragamentoNPalet(String tempoMedCarragamentoNPalet) {
		this.tempoMedCarragamentoNPalet = tempoMedCarragamentoNPalet;
	}
	
	public Long getTempoEsperaMilis() {
		return tempoEsperaMilis;
	}

	public void setTempoEsperaMilis(Long tempoEsperaMilis) {
		this.tempoEsperaMilis = tempoEsperaMilis;
	}
	
	public Long getTempoCarregamentoMilis() {
		return tempoCarregamentoMilis;
	}

	public void setTempoCarregamentoMilis(Long tempoCarregamentoMilis) {
		this.tempoCarregamentoMilis = tempoCarregamentoMilis;
	}
	
	public Long getTempoMedCarregamentoPaletMilis() {
		return tempoMedCarregamentoPaletMilis;
	}

	public void setTempoMedCarregamentoPaletMilis(Long tempoMedCarregamentoPaletMilis) {
		this.tempoMedCarregamentoPaletMilis = tempoMedCarregamentoPaletMilis;
	}
	
	public Long getTempoMedCarregamentoNPaletMilis() {
		return tempoMedCarregamentoNPaletMilis;
	}

	public void setTempoMedCarregamentoNPaletMilis(Long tempoMedCarregamentoNPaletMilis) {
		this.tempoMedCarregamentoNPaletMilis = tempoMedCarregamentoNPaletMilis;
	}

	@Override
	public int compareTo(Tempos o) {
		return this.getData().compareTo(o.getData());
	}
}