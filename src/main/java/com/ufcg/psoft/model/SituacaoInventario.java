package com.ufcg.psoft.model;

public enum SituacaoInventario {

	DISPONIVEL ("DISPONIVEL"),
	INDISPONIVEL ("INDISPONIVEL");
	
	private final String situacao;
	
	SituacaoInventario(String situacao) {
		this.situacao = situacao;
	}
	
	public SituacaoInventario fromString(String situacao) {
		return SituacaoInventario.valueOf(situacao);
	}
	
	public String toString() {
		return situacao;
	}
}
