package com.ufcg.psoft.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.DTO.DisponibilidadeProdutoDTO;
import com.ufcg.psoft.model.DTO.ProdutoDTO;
import com.ufcg.psoft.model.repositories.ProdutoDAO;

@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService {


	@Autowired
	private ProdutoDAO produtoRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Override
	public List<Produto> findAllProdutos(String order) {
		
		if(order.equals("nome")) {
			return 	produtoRepository.getProdutoByNome();
		}
		else if(order.equals("categoria")) {
			return 	produtoRepository.getProdutoByCategoria();
		}
		else if(order.equals("preco")) {
			return 	produtoRepository.getProdutoByPreco();
		}else {
			return produtoRepository.findAll();
		}
	}

	@Override
	public Produto saveProduto(ProdutoDTO produtoDTO) throws Exception {
		if (produtoDTO.getId() == null) {
			
			String nome = produtoDTO.getNome();
			String codigoBarra = produtoDTO.getCodigoBarra();
			String fabricante = produtoDTO.getFabricante();
			String nomeCategoria = produtoDTO.getNomeCategoria();
			BigDecimal preco = produtoDTO.getPreco();
			
			Categoria categoria;
			
			try {
				categoria = this.categoriaService.findCategoriaByName(nomeCategoria);
			}
			catch (Exception e) {
				categoria = this.categoriaService.saveCategoria(new Categoria(nomeCategoria));
			}
			
			Produto produto = new Produto(nome, codigoBarra, fabricante, categoria, preco);
			
			InventarioProduto inventario = new InventarioProduto(produto);
			produto.setIventario(inventario);
			return this.produtoRepository.save(produto);
		}
		else {
			throw new Exception("O ID deve ser determinado pelo sistema.");
		}
	}

	@Override
	public Produto updateProduto(Produto produto) throws Exception {
		long id = produto.getId();
		
		if (this.produtoRepository.existsById(id)) {
			return this.produtoRepository.save(produto);
		}
		else {
			throw new Exception("Produto com id: " + id +", não está contido no banco de dados.");
		}
	}

	@Override
	public void deleteProdutoById(long produtoId) throws Exception {
		if (this.produtoRepository.existsById(produtoId)) {
			this.produtoRepository.deleteById(produtoId);
		}
		else {
			throw new Exception("Produto com id: " + produtoId +", não está contido no banco de dados.");
		}
	}

	@Override
	public long quantityOfProdutos() {
		return this.produtoRepository.count();
	}

	public void deleteAllProdutos() {
		this.produtoRepository.deleteAll();
	}
	
	@Override
	public Produto findProdutoById(long produtoId) throws Exception {
		try {
			return this.produtoRepository.findById(produtoId).get();
		}
		catch(Exception e)  {
			throw new Exception("Produto com id: " + produtoId +", não está contido no banco de dados.");
		}
	}
	
	@Override
	public List<Produto> findAllProdutosByNomeAndFabricante(String nome, String fabricante) {
		return this.produtoRepository.findProdutoByNomeAndFabricante(nome, fabricante);
	}

	@Override
	public boolean doesProdutoExist(Produto produto) {
		return this.produtoRepository.existsById(produto.getId());
	}

	@Override
	public BigDecimal calculaValorDescontoProduto(Produto produto) {
		return produto.getValorDesconto();
	}

	@Override
	public DisponibilidadeProdutoDTO checaDisponibilidadeProduto(Produto produto) throws Exception {
		
		if (produto.produtoEstaDisponivel()) {
			Long quantidade = produto.getIventario().getQuantidadeDisponivel();
			BigDecimal preco = produto.getPreco();
			DisponibilidadeProdutoDTO resposta = new DisponibilidadeProdutoDTO(quantidade, preco);
			return resposta;
		}
		else {
			throw new Exception("Produto se encontra indispinível no momento.");
		}
	}

}
