package com.ufcg.psoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.RegistroVenda;
import com.ufcg.psoft.model.DTO.InventarioProdutoDTO;
import com.ufcg.psoft.model.DTO.ProdutoDTO;
import com.ufcg.psoft.model.DTO.RelatorioMercadoDTO;
import com.ufcg.psoft.model.DTO.RelatorioVendaDTO;
import com.ufcg.psoft.service.ProdutoService;
import com.ufcg.psoft.service.ProdutoServiceImpl;
import com.ufcg.psoft.service.RegistroVendaService;
import com.ufcg.psoft.service.RegistroVendaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api("API REST Mercado")
@CrossOrigin()
public class MercadoController {
	
	@Autowired
	ProdutoService produtoService = new ProdutoServiceImpl();
	
	@Autowired
	RegistroVendaService registroVendaService = new RegistroVendaServiceImpl();
	
	
	
	@RequestMapping(value = "/admin/mercado/", method = RequestMethod.GET)
	@ApiOperation(value="Tem como função retornar um reletório geral da loja, envolvendo dados de estoque e de vendas.")
	public ResponseEntity<?> relatorioMercado() {
		List<Produto> produtos = produtoService.findAllProdutos("none");
		
		List<RegistroVenda> vendas = this.registroVendaService.findAll();
				
		List<InventarioProdutoDTO> inventarios = new ArrayList<InventarioProdutoDTO>();
		produtos.forEach(produto -> inventarios.add(InventarioProdutoDTO.fromInventarioProduto(produto.getIventario())) );		
		RelatorioVendaDTO relatorioVendas = RelatorioVendaDTO.fromCompleteRegistroVendas(vendas); 
		
		RelatorioMercadoDTO resposta = new RelatorioMercadoDTO();
		resposta.setRelatorioInventario(inventarios);
		resposta.setRelatorioVendas(relatorioVendas);

		return new ResponseEntity<RelatorioMercadoDTO>(resposta, HttpStatus.OK);

	}

}
