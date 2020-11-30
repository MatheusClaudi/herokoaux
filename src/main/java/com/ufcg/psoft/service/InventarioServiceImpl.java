package com.ufcg.psoft.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.LoteVenda;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.RegistroVenda;
import com.ufcg.psoft.model.DTO.ItemRegistroVendaDTO;
import com.ufcg.psoft.model.DTO.RegistroVendaDTO;
import com.ufcg.psoft.model.repositories.InventarioProdutoDAO;
import com.ufcg.psoft.model.repositories.LoteDAO;

@Service("inventarioSerive")
public class InventarioServiceImpl implements InventarioService {

	
	@Autowired
	RegistroVendaService registroVendaService;
	
	@Autowired
	LoteService loteService;
	
	@Autowired
	private InventarioProdutoDAO inventarioRepository;

	@Autowired
	ProdutoService produtoService;
	
	@Override
	public void addLoteToInventarioProduto(Lote lote, InventarioProduto inventario) {
		
		inventario.getLotesProduto().add(lote);
		inventario.setQuantidadeLotes( inventario.getQuantidadeLotes() + Long.valueOf(1) );
		inventario.aumenteQuantidade(Long.valueOf(lote.getNumeroDeItens()));
		
		this.inventarioRepository.save(inventario);
		
	}

	@Override
	public ItemRegistroVenda verificarRegistroVendaLote(Produto produto, int quantidade) throws Exception {
		
		InventarioProduto inventario = produto.getIventario();
		
		if (quantidade <= inventario.getQuantidadeDisponivel()) {
			
			List<LoteVenda> lotesVenda = new ArrayList<LoteVenda>();
			Iterator<Lote> i = inventario.getLotesProduto().iterator();
			ItemRegistroVenda irv = new ItemRegistroVenda();
			
			int missing = quantidade;
			
			while(missing != 0) {
				
				Lote next = i.next();
				int disp = next.getNumeroDeItens();
				
				LoteVenda lv = new LoteVenda();
				lv.setLoteUsado(next);
				lv.setItemResgistroVenda(irv);
				 
				if (disp >= missing) {
					lv.setQuantidade(missing);
					missing = 0;
				}
				else {
					lv.setQuantidade(disp);
					missing -= disp;
				}
				
				lotesVenda.add(lv);
			}
			
			BigDecimal descontos = this.produtoService.calculaValorDescontoProduto(produto);
			BigDecimal valor = produto.getPreco().subtract(descontos);	
			
			
			irv.setDescontosAplicados(descontos.multiply(BigDecimal.valueOf(quantidade)));
			irv.setValor(valor.multiply(BigDecimal.valueOf(quantidade)));
			irv.setQuantidade(quantidade);
			irv.setProduto(produto);
			irv.setLotesUtilizados(lotesVenda);

			return irv;
		}
		
		throw new Exception("Quantidade de produtos solicitados não diponível.");
	}

	@Override
	public void efetivaRegistroVendaLote(List<ItemRegistroVenda> lirv) throws Exception {
		Iterator<ItemRegistroVenda> iregistroVenda = lirv.iterator();
		
		while(iregistroVenda.hasNext()) {
			
			ItemRegistroVenda irv = iregistroVenda.next();
			
			Iterator<LoteVenda> ilotes = irv.getLotesUtilizados().iterator();
			
			while (ilotes.hasNext()) {
				
				LoteVenda lv = ilotes.next();
				Lote lote = lv.getLoteUsado();
				lote.diminuiQuantidade(Long.valueOf(lv.getQuantidade()));
				this.loteService.updateLote(lote);
			}
		}
		
	}

	@Override
	public RegistroVenda registraVendaProdutos(RegistroVendaDTO registroVendaDTO) throws Exception {

		List<ItemRegistroVendaDTO> itensAdicionar =  registroVendaDTO.getItens();
		RegistroVenda registroVenda = new RegistroVenda();
		
		Iterator<ItemRegistroVendaDTO> i = itensAdicionar.iterator();
		
		List<ItemRegistroVenda> lirv = new ArrayList<ItemRegistroVenda>();
		BigDecimal valorTotal =  BigDecimal.valueOf(0);
		BigDecimal descontosTotais = BigDecimal.valueOf(0);
		

		
		while(i.hasNext()) {
			ItemRegistroVendaDTO next = i.next();
			Produto produto = this.produtoService.findProdutoById(next.getProdutoId());
			InventarioProduto inventario = produto.getIventario();
			
			Integer quantidade = next.getQuantidade();
			
			ItemRegistroVenda irv = this.verificarRegistroVendaLote(produto, quantidade);
			 
			irv.setRegistroVenda(registroVenda);
			
			inventario.diminuiQuantidade(Long.valueOf(quantidade));
			this.inventarioRepository.save(inventario);
			
			lirv.add(irv);
			valorTotal = valorTotal.add(irv.getValor());
			descontosTotais = descontosTotais.add(irv.getDescontosAplicados());
		}		
		
		
		this.efetivaRegistroVendaLote(lirv);
		registroVenda.setItensVenda(lirv);
		registroVenda.setDescontosTotaisAplicados(descontosTotais);
		registroVenda.setValorTotal(valorTotal);
		
		return this.registroVendaService.saveRegistroVenda(registroVenda);
	}

	@Override
	public void reverteRegistroVendaLote(List<ItemRegistroVenda> lirv) throws Exception {
		Iterator<ItemRegistroVenda> iregistroVenda = lirv.iterator();
		
		while(iregistroVenda.hasNext()) {
			
			ItemRegistroVenda irv = iregistroVenda.next();
			
			Iterator<LoteVenda> ilotes = irv.getLotesUtilizados().iterator();
			
			while (ilotes.hasNext()) {
				
				LoteVenda lv = ilotes.next();
				Lote lote = lv.getLoteUsado();
				
				InventarioProduto inventario = lote.getProduto().getIventario();
				inventario.aumenteQuantidade(Long.valueOf(lv.getQuantidade()));
				lote.aumenteQuantidade(Long.valueOf(lv.getQuantidade()));
				this.loteService.updateLote(lote);
				this.inventarioRepository.save(inventario);
			}
			
			
		}
	}
}
