package com.ufcg.psoft.model;

public enum  NomesDescontoCategoria {
	
	SEM_DESCONTO ("SEM_DESCONTO"),
	BOM_DESCONTO ("BOM_DESCONTO"),
	OTIMO_DESCONTO ("OTIMO_DESCONTO"),
	SUPER_DESCONTO ("SUPER_DESCONTO");
	
	private final String desconto;
	
	NomesDescontoCategoria(String desconto) {
		this.desconto = desconto;
	}
	
	public NomesDescontoCategoria fromString(String desconto) {
		return NomesDescontoCategoria.valueOf(desconto);
	}
	
	public String toString() {
		return desconto;
	}

}
