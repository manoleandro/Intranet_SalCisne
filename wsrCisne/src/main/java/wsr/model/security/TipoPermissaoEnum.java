package wsr.model.security;

public enum TipoPermissaoEnum {
	
	CONSULTA("Consulta"),
	INCLUSAO("Inclus�o"),
	ALTERACAO("Altera��o"),
	EXCLUSAO("Exclus�o");
	
	private String descricao;
	
	private TipoPermissaoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
