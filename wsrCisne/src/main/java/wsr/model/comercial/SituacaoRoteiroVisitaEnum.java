package wsr.model.comercial;

public enum SituacaoRoteiroVisitaEnum {
	A ("Alterado"),
	E ("Efetivo"),
	N ("Não Planejado");
	
	private final String descricao;
	
	private SituacaoRoteiroVisitaEnum(String descricao){
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