package com.ufcg.psoft.model.DTO;

import java.math.BigDecimal;

public class DisponibilidadeProdutoDTO {
	
	private Long quantidade;
	
	private BigDecimal preco;

	public DisponibilidadeProdutoDTO() {}
	
	public DisponibilidadeProdutoDTO(Long quantidade, BigDecimal preco) {
		super();
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	

}
