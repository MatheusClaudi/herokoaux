package com.ufcg.psoft.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SUPER")
public class SuperDesconto extends DescontoCategoria {

	public SuperDesconto() {
		super();
	}
	
	@Override
	public NomesDescontoCategoria getNomeDescontoCategoria() {
		return NomesDescontoCategoria.SUPER_DESCONTO;
	}
	
	@Override
	public double getPorcentagemDesconto() {
		return 50;
	}

}
