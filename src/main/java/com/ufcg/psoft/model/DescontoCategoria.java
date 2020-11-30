package com.ufcg.psoft.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "DESCONTO_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class DescontoCategoria {
	
	@Id
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Categoria categoria;

	
	public DescontoCategoria() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public abstract NomesDescontoCategoria getNomeDescontoCategoria();
	
	public abstract double getPorcentagemDesconto();
	
	public Map<NomesDescontoCategoria, DescontoCategoria> getSubclassMapping() {
		
		Map<NomesDescontoCategoria, DescontoCategoria> mapa = new HashMap<NomesDescontoCategoria, DescontoCategoria>();
		
		mapa.put(NomesDescontoCategoria.SEM_DESCONTO, new SemDesconto());
		mapa.put(NomesDescontoCategoria.OTIMO_DESCONTO, new OtimoDesconto());
		mapa.put(NomesDescontoCategoria.BOM_DESCONTO, new BomDesconto());
		mapa.put(NomesDescontoCategoria.SUPER_DESCONTO, new SuperDesconto());
		
		return mapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescontoCategoria other = (DescontoCategoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
