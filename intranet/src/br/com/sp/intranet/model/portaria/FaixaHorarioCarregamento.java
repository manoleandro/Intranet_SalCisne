package br.com.sp.intranet.model.portaria;

import java.util.Date;

public class FaixaHorarioCarregamento implements Comparable<FaixaHorarioCarregamento>{
	
	private String mes;
	private Long qtd1Turno;
	private Long qtd2Turno;
	private Long qtdTotal;
	private String perc1Turno;
	private String perc2Turno;
	private int qtdDiasMes;
	private Double med1Turno;
	private Double med2Turno;
	private String detalhe;
	private Date data;
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public Long getQtd1Turno() {
		return qtd1Turno;
	}
	
	public void setQtd1Turno(Long qtd1Turno) {
		this.qtd1Turno = qtd1Turno;
	}
	
	public Long getQtd2Turno() {
		return qtd2Turno;
	}
	
	public void setQtd2Turno(Long qtd2Turno) {
		this.qtd2Turno = qtd2Turno;
	}
	
	public Long getQtdTotal() {
		return qtdTotal;
	}
	
	public void setQtdTotal(Long qtdTotal) {
		this.qtdTotal = qtdTotal;
	}
	
	public String getPerc1Turno() {
		return perc1Turno;
	}

	public void setPerc1Turno(String perc1Turno) {
		this.perc1Turno = perc1Turno;
	}

	public String getPerc2Turno() {
		return perc2Turno;
	}

	public void setPerc2Turno(String perc2Turno) {
		this.perc2Turno = perc2Turno;
	}

	public int getQtdDiasMes() {
		return qtdDiasMes;
	}

	public void setQtdDiasMes(int qtdDiasMes) {
		this.qtdDiasMes = qtdDiasMes;
	}

	public Double getMed1Turno() {
		return med1Turno;
	}
	
	public void setMed1Turno(Double med1Turno) {
		this.med1Turno = med1Turno;
	}
	
	public Double getMed2Turno() {
		return med2Turno;
	}
	
	public void setMed2Turno(Double med2Turno) {
		this.med2Turno = med2Turno;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int compareTo(FaixaHorarioCarregamento o) {
		return this.getData().compareTo(o.data);
	}
}