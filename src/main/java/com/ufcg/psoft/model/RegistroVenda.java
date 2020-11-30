package com.ufcg.psoft.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="REGISTRO_VENDA_TB")
public class RegistroVenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private BigDecimal valorTotal;
	
	private BigDecimal descontosTotaisAplicados;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "registroVenda", orphanRemoval=true)
	private List<ItemRegistroVenda> itensVenda;
	
	public RegistroVenda() {}

	public RegistroVenda(BigDecimal valorTotal, BigDecimal descontosTotaisAplicados,
			List<ItemRegistroVenda> itensVenda) {
		super();
		this.valorTotal = valorTotal;
		this.descontosTotaisAplicados = descontosTotaisAplicados;
		this.itensVenda = itensVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getDescontosTotaisAplicados() {
		return descontosTotaisAplicados;
	}

	public void setDescontosTotaisAplicados(BigDecimal descontosTotaisAplicados) {
		this.descontosTotaisAplicados = descontosTotaisAplicados;
	}

	public List<ItemRegistroVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemRegistroVenda> itensVenda) {
		this.itensVenda = itensVenda;
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
		RegistroVenda other = (RegistroVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
