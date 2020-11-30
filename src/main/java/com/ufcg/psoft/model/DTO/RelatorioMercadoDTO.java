package com.ufcg.psoft.model.DTO;

import java.util.List;

public class RelatorioMercadoDTO {
	
	private List<InventarioProdutoDTO> relatorioInventario;
	
	private RelatorioVendaDTO relatorioVendas;

	public RelatorioMercadoDTO() {
		
	}
	
	public RelatorioMercadoDTO(List<InventarioProdutoDTO> relatorioInventario, RelatorioVendaDTO relatorioVendas) {
		super();
		this.relatorioInventario = relatorioInventario;
		this.relatorioVendas = relatorioVendas;
	}

	public List<InventarioProdutoDTO> getRelatorioInventario() {
		return relatorioInventario;
	}

	public void setRelatorioInventario(List<InventarioProdutoDTO> relatorioInventario) {
		this.relatorioInventario = relatorioInventario;
	}

	public RelatorioVendaDTO getRelatorioVendas() {
		return relatorioVendas;
	}

	public void setRelatorioVendas(RelatorioVendaDTO relatorioVendas) {
		this.relatorioVendas = relatorioVendas;
	}	
}
