package br.com.sp.intranet.model.portaria;

import java.util.Date;

public class TotalPeso implements Comparable<TotalPeso>{
	
	private String mes;
	private Double qtdFOB;
	private Double qtdCIF;
	private Double qtdPalet;
	private Double qtdManual;
	private Double qtdTotal;
	private Date data;
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}

	public Double getQtdFOB() {
		return qtdFOB;
	}

	public void setQtdFOB(Double qtdFOB) {
		this.qtdFOB = qtdFOB;
	}

	public Double getQtdCIF() {
		return qtdCIF;
	}

	public void setQtdCIF(Double qtdCIF) {
		this.qtdCIF = qtdCIF;
	}

	public Double getQtdPalet() {
		return qtdPalet;
	}

	public void setQtdPalet(Double qtdPalet) {
		this.qtdPalet = qtdPalet;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getQtdManual() {
		return qtdManual;
	}

	public void setQtdManual(Double qtdManual) {
		this.qtdManual = qtdManual;
	}

	public Double getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(Double qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	@Override
	public int compareTo(TotalPeso o) {
		return this.data.compareTo(o.data);
	}	
}