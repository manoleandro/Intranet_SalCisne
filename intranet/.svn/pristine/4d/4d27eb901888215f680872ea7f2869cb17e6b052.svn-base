package br.com.sp.intranet.model.administrador.vo.rh;

import java.util.Arrays;
import java.util.List;

public enum FilialEnum {
	TO(null, "Todas"),
	CF(Arrays.asList(new Long(141), new Long(140)) , "Cabo Frio"), 
	SP(Arrays.asList(new Long(240)), "São Paulo");
	
	private List<Long> values;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Long> getValues() {
		return values;
	}

	public void setValues(List<Long> values) {
		this.values = values;
	}

	private FilialEnum(List<Long> values, String descricao) {
		this.values = values;
		this.descricao = descricao;
	}
}