package br.com.sp.intranet.controller.administrador;

public enum TipoFuncionarioEnum {

	COLABORADOR("Colaborador"), 
	GESTOR("Gestor"), 
	COORDENADOR("Coordenador");

	private final String descricao;

	private TipoFuncionarioEnum(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

	public String getDescricao() {
		return descricao;
	}
}