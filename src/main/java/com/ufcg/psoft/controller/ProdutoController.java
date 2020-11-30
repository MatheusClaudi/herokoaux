package com.ufcg.psoft.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.DTO.DisponibilidadeProdutoDTO;
import com.ufcg.psoft.model.DTO.LoteDTO;
import com.ufcg.psoft.model.DTO.ProdutoDTO;
import com.ufcg.psoft.service.CategoriaService;
import com.ufcg.psoft.service.CategoriaServiceImpl;
import com.ufcg.psoft.service.InventarioService;
import com.ufcg.psoft.service.InventarioServiceImpl;
import com.ufcg.psoft.service.LoteService;
import com.ufcg.psoft.service.LoteServiceImpl;
import com.ufcg.psoft.service.ProdutoService;
import com.ufcg.psoft.service.ProdutoServiceImpl;
import com.ufcg.psoft.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api("API REST Produto")
@CrossOrigin
public class ProdutoController {

	@Autowired
	ProdutoService produtoService = new ProdutoServiceImpl();
	
	@Autowired
	CategoriaService categoriaService = new CategoriaServiceImpl();
	
	@Autowired
	LoteService loteService = new LoteServiceImpl();
	
	
	@RequestMapping(value = "/admin/produto/", method = RequestMethod.GET)
	@ApiOperation(value="Possui funão de listar todos os produtos cadastrados no sistema, podendo ou não ordenar os resultados de acordo"
			+ "com suas caracteristicas, isto é de acordo com order, onde order pode ser nome, categoria e preco.")
	public ResponseEntity<?> listProdutos(@RequestParam (required = false, defaultValue = "none") String order) {
		List<Produto> produtos = produtoService.findAllProdutos(order);

		if (produtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			List<ProdutoDTO> retorno = new ArrayList<ProdutoDTO>(); 
			produtos.forEach(produto -> retorno.add(ProdutoDTO.fromCompleteProduto(produto)));
			
			return new ResponseEntity<List<ProdutoDTO>>(retorno, HttpStatus.OK);
		}
	}

	@ApiOperation(value="Possui a funcao de cadastrar um produto no sistema.")
	@RequestMapping(value = "/admin/produto/", method = RequestMethod.POST)
	public ResponseEntity<?> createProduto(@RequestBody ProdutoDTO produtoDTO) {

		String nome = produtoDTO.getNome();
		String fabricante = produtoDTO.getNome();
		
		List<Produto> produtosComMesmoNomeEFabricante = this.produtoService.findAllProdutosByNomeAndFabricante(nome, fabricante);
		
		if (!produtosComMesmoNomeEFabricante.isEmpty()) {
			CustomErrorType error = new CustomErrorType("O produto " + nome + " do fabricante " + fabricante + " ja esta cadastrado!");
			return new ResponseEntity<>(error , HttpStatus.CONFLICT);

		}
		
		try {
			Produto produtoCriado = produtoService.saveProduto(produtoDTO);
			
			ProdutoDTO retorno = ProdutoDTO.fromCompleteProduto(produtoCriado);
			
			return new ResponseEntity<ProdutoDTO>(retorno, HttpStatus.CREATED);
		}
		catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/admin/produto/{id}", method = RequestMethod.GET)
	@ApiOperation(value="Consulta de informações de produto restrita à Admin (Retorna informaçoes mais detalhadas).")
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) {

		try {
			Produto produto = this.produtoService.findProdutoById(id);
			ProdutoDTO retorno = ProdutoDTO.fromCompleteProduto(produto);
			return new ResponseEntity<ProdutoDTO>(retorno, HttpStatus.OK);
		}
		catch(Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/public/produto/{id}/disponibilidade/", method = RequestMethod.GET)
	@ApiOperation(value="Consulta de disponibilidade de produto publica para todos os usuários.")
	public ResponseEntity<?> consultarDisponibilidadeProduto(@PathVariable("id") long id) {

		try {
			Produto produto = this.produtoService.findProdutoById(id);
			DisponibilidadeProdutoDTO retorno = this.produtoService.checaDisponibilidadeProduto(produto);
			return new ResponseEntity<DisponibilidadeProdutoDTO>(retorno, HttpStatus.OK);
		}
		catch(Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/admin/produto/{id}", method = RequestMethod.PUT)
	@ApiOperation(value="Atualiza os valores de produto de acordo as mudanças solicitadas.")
	public ResponseEntity<?> updateProduto(@PathVariable("id") long id, @RequestBody Produto produto) {		
		try {
			Produto produtoAlterado = this.produtoService.updateProduto(produto);
			
			ProdutoDTO resposta = ProdutoDTO.fromCompleteProduto(produtoAlterado);
			
			return new ResponseEntity<ProdutoDTO>(resposta, HttpStatus.OK);
		}
		catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/admin/produto/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="Deleta um produto do sistema, só será permitido caso o produto não esteja relacionado com nehuma outr entidade.")
	public ResponseEntity<?> deleteProduto(@PathVariable("id") long id) {
		try {
			this.produtoService.deleteProdutoById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
		}
	}
}