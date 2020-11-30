package com.ufcg.psoft.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="INVENTARIO_PRODUTO_TB")
public class InventarioProduto {
	
	@Id
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Produto produto;
	
	@OneToMany(fetch = FetchType.LAZY)
	@OrderBy("numeroDeItens DESC")
	private List<Lote> lotesProduto;
	
	private Long quantidadeLotes;
	
	private Long quantidadeDisponivel;

	
	public InventarioProduto() {}

	public InventarioProduto(Produto produto) {
		super();
		this.quantidadeDisponivel = Long.valueOf(0);
		
		this.quantidadeLotes = Long.valueOf(0);
		this.lotesProduto = new ArrayList();
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Lote> getLotesProduto() {
		return lotesProduto;
	}

	public void setLotesProduto(List<Lote> lotesProduto) {
		this.lotesProduto = lotesProduto;
	}
	
	public Long getQuantidadeLotes() {
		return quantidadeLotes;
	}

	public void setQuantidadeLotes(Long quantidadeLotes) {
		this.quantidadeLotes = quantidadeLotes;
	}

	public Long getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Long quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}


	public void diminuiQuantidade(Long quantidade) {
		this.quantidadeDisponivel -= quantidade;
		this.analiseQuantidade();
	}
	
	public void aumenteQuantidade(Long quantidade) {
		this.quantidadeDisponivel += quantidade;
		this.analiseQuantidade();
	}
	
	public void analiseQuantidade() {
		if (this.quantidadeDisponivel == 0) {
			this.produto.setSituacaoIndisponivel();
		}
		else {
			this.produto.setSituacaoDisponivel();
		}
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
		InventarioProduto other = (InventarioProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
