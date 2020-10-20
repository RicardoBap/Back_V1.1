package com.ricbap.salvavidas.api.model;

public enum TipoLancamento {
	
	RECEITA("Entrada"),
	DESPESA("Saída");
	
	private final String descricao;
	
	TipoLancamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
