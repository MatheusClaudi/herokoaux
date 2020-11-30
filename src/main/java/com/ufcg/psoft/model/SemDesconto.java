package com.ufcg.psoft.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SEM")
public class SemDesconto extends DescontoCategoria {
	
	public SemDesconto() {
		super();
	}
	
	@Override
	public NomesDescontoCategoria getNomeDescontoCategoria() {
		return NomesDescontoCategoria.SEM_DESCONTO;
	}
	
	@Override
	public double getPorcentagemDesconto() {
		return 0;
	}

}
