package com.ufcg.psoft.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.RegistroVenda;

public class RelatorioVendaDTO {
	
	private List<RegistroVendaDTO> registroVendas;
	
	private BigDecimal receitaTotal;

	public RelatorioVendaDTO() {}
	
	public RelatorioVendaDTO(List<RegistroVendaDTO> registroVendas, BigDecimal receitaTotal) {
		super();
		this.registroVendas = registroVendas;
		this.receitaTotal = receitaTotal;
	}

	public List<RegistroVendaDTO> getRegistroVendas() {
		return registroVendas;
	}

	public void setRegistroVendas(List<RegistroVendaDTO> registroVendas) {
		this.registroVendas = registroVendas;
	}

	public BigDecimal getReceitaTotal() {
		return receitaTotal;
	}

	public void setReceitaTotal(BigDecimal receitaTotal) {
		this.receitaTotal = receitaTotal;
	}
	
	
	public static RelatorioVendaDTO fromCompleteRegistroVendas(List<RegistroVenda> registroVendas) {
		
		RelatorioVendaDTO relatorio = new RelatorioVendaDTO();
		BigDecimal receita = BigDecimal.valueOf(0); 
		
		List<RegistroVendaDTO> vendas = new ArrayList<RegistroVendaDTO>();
		
		Iterator<RegistroVenda> i = registroVendas.iterator();
		
		while(i.hasNext()) {
			RegistroVenda registro = i.next();
			RegistroVendaDTO rvDTO = RegistroVendaDTO.fromCompleteRegistroVenda(registro);
			vendas.add(rvDTO);
			receita = receita.add(rvDTO.getValorTotal());
		}
		
		relatorio.setReceitaTotal(receita);
		relatorio.setRegistroVendas(vendas);
		
		return relatorio;
	}
	
	
	

}
