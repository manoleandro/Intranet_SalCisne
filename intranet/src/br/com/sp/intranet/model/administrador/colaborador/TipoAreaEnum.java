package br.com.sp.intranet.model.administrador.colaborador;

public enum TipoAreaEnum {
	
	DIRETORIA ("Diretoria"),
	DEPARTAMENTO ("Departamento"),
	SETOR ("Setor"),
	SECAO ("Seção");
	
	private final String descricao;
	
	private TipoAreaEnum(String descricao){
		this.descricao = descricao;
	}
	
	@Override
	public String toString(){
		return getDescricao();
	}

	public String getDescricao() {
		return descricao;
	}	
}