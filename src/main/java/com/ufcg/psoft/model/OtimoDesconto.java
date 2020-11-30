package com.ufcg.psoft.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "OTIMO")
public class OtimoDesconto extends DescontoCategoria {
	
	public OtimoDesconto() {
		super();
	}
	
	@Override
	public NomesDescontoCategoria getNomeDescontoCategoria() {
		return NomesDescontoCategoria.OTIMO_DESCONTO;
	}
	
	@Override
	public double getPorcentagemDesconto() {
		return 25;
	}

}
