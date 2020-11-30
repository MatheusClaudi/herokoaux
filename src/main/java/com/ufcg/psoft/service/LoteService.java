package com.ufcg.psoft.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.LoteVenda;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.RegistroVenda;
import com.ufcg.psoft.model.DTO.LoteDTO;

public interface LoteService {

	List<Lote> findAllLotes();

	Lote findLoteById(long id) throws Exception;

	void updateLote(Lote user) throws Exception;

	void deleteLoteById(long id) throws Exception;

	long quantityOfLotes();

	Lote saveLote(LoteDTO loteDTO, Produto produto) throws Exception;
	
	List<Lote> findAllLotesEmFalta();
	
	List<Lote> findAllLotesVencendo();
	
}
