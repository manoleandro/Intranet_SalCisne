package br.com.sp.intranet.model.portaria;

public class PesoCarregadoTurno {
	
	private String tipoCarga;
	private Double qtd1CIF;
	private Double qtd1FOB;
	private Double qtd1Total;
	private Double qtd2CIF;
	private Double qtd2FOB;
	private Double qtd2Total;
	private Double totalGeral;
	private String percTurno1;
	private String percTurno2;
	private String percTotal;
	
	public String getTipoCarga() {
		return tipoCarga;
	}
	
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	
	public Double getQtd1CIF() {
		return qtd1CIF;
	}
	
	public void setQtd1CIF(Double qtd1cif) {
		qtd1CIF = qtd1cif;
	}
	
	public Double getQtd1FOB() {
		return qtd1FOB;
	}
	
	public void setQtd1FOB(Double qtd1fob) {
		qtd1FOB = qtd1fob;
	}
	
	public Double getQtd1Total() {
		return qtd1Total;
	}
	
	public void setQtd1Total(Double qtd1Total) {
		this.qtd1Total = qtd1Total;
	}
	
	public Double getQtd2CIF() {
		return qtd2CIF;
	}
	
	public void setQtd2CIF(Double qtd2cif) {
		qtd2CIF = qtd2cif;
	}
	
	public Double getQtd2FOB() {
		return qtd2FOB;
	}
	
	public void setQtd2FOB(Double qtd2fob) {
		qtd2FOB = qtd2fob;
	}
	
	public Double getQtd2Total() {
		return qtd2Total;
	}
	
	public void setQtd2Total(Double qtd2Total) {
		this.qtd2Total = qtd2Total;
	}
	
	public Double getTotalGeral() {
		return totalGeral;
	}
	
	public void setTotalGeral(Double totalGeral) {
		this.totalGeral = totalGeral;
	}
	
	public String getPercTurno1() {
		return percTurno1;
	}
	
	public void setPercTurno1(String percTurno1) {
		this.percTurno1 = percTurno1;
	}
	
	public String getPercTurno2() {
		return percTurno2;
	}
	
	public void setPercTurno2(String percTurno2) {
		this.percTurno2 = percTurno2;
	}
	
	public String getPercTotal() {
		return percTotal;
	}
	
	public void setPercTotal(String percTotal) {
		this.percTotal = percTotal;
	}
}