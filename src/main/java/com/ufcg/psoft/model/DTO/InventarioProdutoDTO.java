package com.ufcg.psoft.model.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufcg.psoft.model.InventarioProduto;

public class InventarioProdutoDTO {
	
	private Long numeroLotes;
	
	private Long numeroItens;
	
	private List<Date> datasValidadeLotes;

	public InventarioProdutoDTO() {}
	
	public InventarioProdutoDTO(Long numeroLotes, Long numeroItens, List<Date> datasValidadeLotes) {
		super();
		this.numeroLotes = numeroLotes;
		this.numeroItens = numeroItens;
		this.datasValidadeLotes = datasValidadeLotes;
	}

	public Long getNumeroLotes() {
		return numeroLotes;
	}

	public void setNumeroLotes(Long numeroLotes) {
		this.numeroLotes = numeroLotes;
	}

	public Long getNumeroItens() {
		return numeroItens;
	}

	public void setNumeroItens(Long numeroItens) {
		this.numeroItens = numeroItens;
	}

	public List<Date> getDatasValidadeLotes() {
		return datasValidadeLotes;
	}

	public void setDatasValidadeLotes(List<Date> datasValidadeLotes) {
		this.datasValidadeLotes = datasValidadeLotes;
	}
	
	public static InventarioProdutoDTO fromInventarioProduto(InventarioProduto inventario) {
		
		InventarioProdutoDTO ipDTO = new InventarioProdutoDTO();
		
		List<Date> datas = new ArrayList<Date>();
		
		inventario.getLotesProduto().forEach(lote -> datas.add(lote.getDataDeValidade()));
		
		ipDTO.setDatasValidadeLotes(datas);
		ipDTO.setNumeroItens(inventario.getQuantidadeDisponivel());
		ipDTO.setNumeroLotes(inventario.getQuantidadeLotes());
		
		return ipDTO;
	}

}
