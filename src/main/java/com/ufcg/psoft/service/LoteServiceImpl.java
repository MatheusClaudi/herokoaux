package com.ufcg.psoft.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.LoteVenda;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.DTO.LoteDTO;
import com.ufcg.psoft.model.repositories.LoteDAO;

@Service("loteService")
public class LoteServiceImpl implements LoteService {

	@Autowired
	private LoteDAO lotesRepository;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	InventarioService iventarioService = new InventarioServiceImpl();

	@Override
	public Lote saveLote(LoteDTO loteDTO, Produto produto) throws Exception {
		
		Lote lote = new Lote(produto, loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade());
		InventarioProduto inventario = produto.getIventario();
		lote.setIventario(inventario);
		
		if (lote.getId() == null) {
			
			Lote loteCriado = this.lotesRepository.save(lote);
			this.iventarioService.addLoteToInventarioProduto(loteCriado, inventario);
			return loteCriado;

		}
		throw new Exception("ID de Lote deve ser determinado pelo sistema.");

	}

	@Override
	public Lote findLoteById(long loteId) throws Exception {
		try {
			return this.lotesRepository.findById(loteId).get();
		}
		catch(Exception e) {
			throw new Exception("Lote com id: " + loteId +", não está contido no banco de dados.");
		}
	}

	@Override
	public void updateLote(Lote lote) throws Exception {
		long id = lote.getId();
		
		if (this.lotesRepository.existsById(id)) {
			this.lotesRepository.save(lote);
		}
		else {
			throw new Exception("Lote com id: " + id +", não está contido no banco de dados.");
		}
	}

	@Override
	public void deleteLoteById(long id) throws Exception {
	
		if (this.lotesRepository.existsById(id)) {
			this.lotesRepository.deleteById(id);
		}
		else {
			throw new Exception("Lote com id: " + id +", não está contido no banco de dados.");
		}
	}

	@Override
	public List<Lote> findAllLotes() {
		return this.lotesRepository.findAll();
	}
	
	@Override
	public List<Lote> findAllLotesEmFalta() {
		return this.lotesRepository.findAllByNumeroDeItens(Lote.getMarcadorDeEstoqueBaixo());
	}

	@Override
	public long quantityOfLotes() {
		return this.lotesRepository.count();
	}

	@Override
	public List<Lote> findAllLotesVencendo() {
		Calendar calendar = Calendar.getInstance(); 
		Date hoje = calendar.getTime();
		Date limiar = Lote.getDataIndicadoraVencimento(hoje);
		return this.lotesRepository.getAllBetweenDates(hoje, limiar);
	}
}
