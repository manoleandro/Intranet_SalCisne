package wsr.model.security;

public enum TipoPermissaoEnum {
	
	CONSULTA("Consulta"),
	INCLUSAO("Inclusão"),
	ALTERACAO("Alteração"),
	EXCLUSAO("Exclusão");
	
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
