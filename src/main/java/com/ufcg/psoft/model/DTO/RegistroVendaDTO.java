package com.ufcg.psoft.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.RegistroVenda;

public class RegistroVendaDTO {
	
private Long id;
	
	private BigDecimal valorTotal;
	
	private BigDecimal descontosTotaisAplicados;
	
	private List<ItemRegistroVendaDTO> itens;
	
	public RegistroVendaDTO() {}

	public RegistroVendaDTO(List<ItemRegistroVendaDTO> itens) {
		super();
		this.itens = itens;
	}

	public List<ItemRegistroVendaDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemRegistroVendaDTO> itens) {
		this.itens = itens;
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
	
	public static RegistroVendaDTO fromCompleteRegistroVenda(RegistroVenda registroVenda) {
		RegistroVendaDTO resposta = new RegistroVendaDTO();
		
		List<ItemRegistroVendaDTO> itens = new ArrayList<ItemRegistroVendaDTO>();
		
		Iterator<ItemRegistroVenda> i = registroVenda.getItensVenda().iterator();
		
		while(i.hasNext()) {
			ItemRegistroVenda next = i.next();
			ItemRegistroVendaDTO newItem = new ItemRegistroVendaDTO(next.getProduto().getId(), next.getQuantidade());
			itens.add(newItem);
		}

		resposta.setDescontosTotaisAplicados(registroVenda.getDescontosTotaisAplicados());
		resposta.setId(registroVenda.getId());
		resposta.setItens(itens);
		resposta.setValorTotal(registroVenda.getValorTotal());
		
		return resposta;
	}
	
	
}
