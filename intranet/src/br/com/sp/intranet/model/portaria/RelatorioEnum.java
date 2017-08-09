package br.com.sp.intranet.model.portaria;

public enum RelatorioEnum {
	
	TEMPO_CARREGAMENTO_TURNO_DETAL("icon-file-excel", "Tempo de Carregamento por Turno Detalhado"),
	VEICULOS_TURNO_DETAL("icon-file-excel", "Veículos por Turno Detalhado"), 
	PESO_CARREGAMENTO_TURNO_DETAL("icon-file-excel", "Peso Carregamento por turno Detalhado"),
	QUANTIDADE_VEICULOS_CARREG("icon-file-excel", "Quantidade de Veículos Carregados"),
	QUANTIDADE_TOTAL_PESO("icon-file-excel", "Quantidade Total de Peso"),
	QUANTIDADE_TIPO_CARREG("icon-file-pdf", "Quantidade por Tipo de Carregamento"),
	FAIXA_HORARIA_APRES("icon-file-pdf", "Faixa Horária de Apresentação"),
	TEMPO_ESPERA_CARREG("icon-file-pdf", "Tempo de Espera Geral/Carregamento"),
	TEMPO_CARREG_TIPO("icon-file-pdf", "Tempo de Carregamento por Tipo");
	
	private String icone;
	private String descricao;
	
	private RelatorioEnum(String icone, String descricao) {
		this.icone = icone;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getIcone() {
		return icone;
	}
	
	public void setIcone(String icone) {
		this.icone = icone;
	}
}