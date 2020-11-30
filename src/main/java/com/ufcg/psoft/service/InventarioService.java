package com.ufcg.psoft.service;

import java.util.List;

import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.RegistroVenda;
import com.ufcg.psoft.model.DTO.ItemRegistroVendaDTO;
import com.ufcg.psoft.model.DTO.RegistroVendaDTO;

public interface InventarioService {
	
	void addLoteToInventarioProduto(Lote lote, InventarioProduto inventario);
	ItemRegistroVenda verificarRegistroVendaLote(Produto produto, int quantidade) throws Exception;
	void efetivaRegistroVendaLote(List<ItemRegistroVenda> lirv) throws Exception;
	RegistroVenda registraVendaProdutos(RegistroVendaDTO registroVendaDTO) throws Exception;
	
	void reverteRegistroVendaLote(List<ItemRegistroVenda> lirv) throws Exception;
}
