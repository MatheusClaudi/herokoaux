package com.ufcg.psoft.model.DTO;

import java.math.BigDecimal;

public class ItemRegistroVendaDTO {
	
	private Long produtoId;
	
	private Integer quantidade;


	public ItemRegistroVendaDTO() {}
	
	public ItemRegistroVendaDTO(Long produtoId, Integer quantidade) {
		super();
		this.produtoId = produtoId;
		this.quantidade = quantidade;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
}
