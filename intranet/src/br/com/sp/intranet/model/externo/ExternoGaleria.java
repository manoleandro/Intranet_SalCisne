package br.com.sp.intranet.model.externo;

import java.util.List;

public class ExternoGaleria {
	private String category;
	private List<Galeria> galerias;

	public ExternoGaleria() {

	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Galeria> getGalerias() {
		return galerias;
	}

	public void setGalerias(List<Galeria> galerias) {
		this.galerias = galerias;
	}

}
