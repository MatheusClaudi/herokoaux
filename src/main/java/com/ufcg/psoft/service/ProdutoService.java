package com.ufcg.psoft.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.DTO.DisponibilidadeProdutoDTO;
import com.ufcg.psoft.model.DTO.ProdutoDTO;

public interface ProdutoService {


	Produto saveProduto(ProdutoDTO produto) throws Exception;

	Produto findProdutoById(long id) throws Exception;

	Produto updateProduto(Produto user) throws Exception;

	void deleteProdutoById(long id)  throws Exception;

	long quantityOfProdutos();

	boolean doesProdutoExist(Produto produto);
	
	List<Produto> findAllProdutosByNomeAndFabricante(String nome, String fabricante);
		
	BigDecimal calculaValorDescontoProduto(Produto produto);
	
	DisponibilidadeProdutoDTO checaDisponibilidadeProduto(Produto produto) throws Exception;

	List<Produto> findAllProdutos(String order);
	
}
