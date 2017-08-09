package br.com.sp.intranet.model.externo;


import java.util.List;

public class ExternoCardapio {
	private String category;
	private List<Cardapio> cardapios;
	
	public ExternoCardapio() {
		
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}
	
	
}
