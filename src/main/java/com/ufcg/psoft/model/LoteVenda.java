package com.ufcg.psoft.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOTE_VENDA_TB")
public class LoteVenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Integer quantidade;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Lote loteUsado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ItemRegistroVenda itemResgistroVenda;

	public LoteVenda() {}
	
	public LoteVenda(Integer quantidade, Lote loteUsado, ItemRegistroVenda itemResgistroVenda) {
		super();
		this.quantidade = quantidade;
		this.loteUsado = loteUsado;
		this.itemResgistroVenda = itemResgistroVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Lote getLoteUsado() {
		return loteUsado;
	}

	public void setLoteUsado(Lote loteUsado) {
		this.loteUsado = loteUsado;
	}

	public ItemRegistroVenda getItemResgistroVenda() {
		return itemResgistroVenda;
	}

	public void setItemResgistroVenda(ItemRegistroVenda itemResgistroVenda) {
		this.itemResgistroVenda = itemResgistroVenda;
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
		LoteVenda other = (LoteVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
