package com.ufcg.psoft.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIA_TB")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nomeCategoria;
	
	@OneToOne(mappedBy="categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private DescontoCategoria desconto;

	public Categoria() {}
	
	public Categoria(String nomeCategoria) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.desconto = new SemDesconto();
		this.desconto.setCategoria(this);
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public DescontoCategoria getDesconto() {
		return desconto;
	}

	public void setDesconto(DescontoCategoria desconto) {
		this.desconto = desconto;
	}
	
	public void modificaDescontoCategoria(NomesDescontoCategoria descontoCategoria) {
		DescontoCategoria novoDesconto = this.desconto.getSubclassMapping().get(descontoCategoria);
		novoDesconto.setCategoria(this);
		this.desconto = novoDesconto;
	}
	
	public double getPorcentagemDesconto() {
		return this.desconto.getPorcentagemDesconto();
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
