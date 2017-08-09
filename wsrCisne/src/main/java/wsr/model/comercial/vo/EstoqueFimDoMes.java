package wsr.model.comercial.vo;

import java.time.LocalDate;

public class EstoqueFimDoMes {
	
	private Long quantidadeEstoque;
	private Long quantidadeEntrada;
	private Long consumoProgressivo;
	private LocalDate dataLancamento;
	
	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Long getQuantidadeEntrada() {
		return quantidadeEntrada;
	}

	public void setQuantidadeEntrada(Long quantidadeEntrada) {
		this.quantidadeEntrada = quantidadeEntrada;
	}

	public Long getConsumoProgressivo() {
		return consumoProgressivo;
	}

	public void setConsumoProgressivo(Long consumoProgressivo) {
		this.consumoProgressivo = consumoProgressivo;
	}
}