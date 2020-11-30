package com.ufcg.psoft.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BOM")
public class BomDesconto extends DescontoCategoria {
	
	public BomDesconto() {
		super();
	}
	
	@Override
	public NomesDescontoCategoria getNomeDescontoCategoria() {
		return NomesDescontoCategoria.BOM_DESCONTO;
	}

	@Override
	public double getPorcentagemDesconto() {
		return 10;
	}

}
