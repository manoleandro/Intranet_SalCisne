package br.com.sp.intranet.model.portaria;

public class QuantidadeVeiculosDetalhado {
	
	private String tipoCarregamento;
	private String turno;
	private Long qtdCIF;
	private Long qtdFOB;
	private Long qtdTotal;
	
	public String getTipoCarregamento() {
		return tipoCarregamento;
	}
	
	public void setTipoCarregamento(String tipoCarregamento) {
		this.tipoCarregamento = tipoCarregamento;
	}
	
	public String getTurno() {
		return turno;
	}
	
	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public Long getQtdCIF() {
		return qtdCIF;
	}
	
	public void setQtdCIF(Long qtdCIF) {
		this.qtdCIF = qtdCIF;
	}
	
	public Long getQtdFOB() {
		return qtdFOB;
	}
	
	public void setQtdFOB(Long qtdFOB) {
		this.qtdFOB = qtdFOB;
	}
	
	public Long getQtdTotal() {
		return qtdTotal;
	}
	
	public void setQtdTotal(Long qtdTotal) {
		this.qtdTotal = qtdTotal;
	}
}