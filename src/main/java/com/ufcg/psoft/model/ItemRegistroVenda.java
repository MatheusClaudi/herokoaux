package com.ufcg.psoft.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEM_REGISTRO_VENDA_TB")
public class ItemRegistroVenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Produto produto;
	
	private Integer quantidade;
	
	private BigDecimal valor;
	
	private BigDecimal descontosAplicados;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private RegistroVenda registroVenda;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval=true)
	private List<LoteVenda> lotesUtilizados;
	
	public ItemRegistroVenda() {}


	public ItemRegistroVenda(Produto produto, Integer quantidade, BigDecimal valor, BigDecimal descontosAplicados, List<LoteVenda> lotesUtilizados) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
		this.descontosAplicados = descontosAplicados;
		this.lotesUtilizados = lotesUtilizados;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDescontosAplicados() {
		return descontosAplicados;
	}

	public void setDescontosAplicados(BigDecimal descontosAplicados) {
		this.descontosAplicados = descontosAplicados;
	}

	public RegistroVenda getRegistroVenda() {
		return registroVenda;
	}

	public void setRegistroVenda(RegistroVenda registroVenda) {
		this.registroVenda = registroVenda;
	}

	public List<LoteVenda> getLotesUtilizados() {
		return lotesUtilizados;
	}

	public void setLotesUtilizados(List<LoteVenda> lotesUtilizados) {
		this.lotesUtilizados = lotesUtilizados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		ItemRegistroVenda other = (ItemRegistroVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
