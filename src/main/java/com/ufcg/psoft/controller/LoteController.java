package com.ufcg.psoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.DTO.LoteDTO;
import com.ufcg.psoft.service.CategoriaService;
import com.ufcg.psoft.service.CategoriaServiceImpl;
import com.ufcg.psoft.service.LoteService;
import com.ufcg.psoft.service.LoteServiceImpl;
import com.ufcg.psoft.service.ProdutoService;
import com.ufcg.psoft.service.ProdutoServiceImpl;
import com.ufcg.psoft.util.CustomErrorType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api("API REST Lote")
@CrossOrigin
public class LoteController {

	@Autowired
	ProdutoService produtoService = new ProdutoServiceImpl();
	
	@Autowired
	CategoriaService categoriaService = new CategoriaServiceImpl();
	
	@Autowired
	LoteService loteService = new LoteServiceImpl();

	
	@RequestMapping(value = "/admin/produto/{id}/lote", method = RequestMethod.POST)
	@ApiOperation(value="Cadastra lotes de produtos no sistema.")
	public ResponseEntity<?> criarLote(@PathVariable("id") long produtoId, @RequestBody LoteDTO loteDTO) {
		
		Produto produto;
		
		try {
			produto = produtoService.findProdutoById(produtoId);
		} catch (Exception e1) {
			CustomErrorType error = new CustomErrorType(e1.getMessage());
			return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
		}

	
		try {
			Lote loteCriado = loteService.saveLote(loteDTO, produto);
			
			LoteDTO resposta = LoteDTO.fromCompleteLote(loteCriado);
			
			return new ResponseEntity<>(resposta, HttpStatus.CREATED);
		}
		catch(Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(value = "/admin/lote/", method = RequestMethod.GET)
	@ApiOperation(value="Lista todos os lotes do sistema.")
	public ResponseEntity<List<LoteDTO>> listAllLotes() {
		List<Lote> lotes = loteService.findAllLotes();

		if (lotes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			List<LoteDTO> retorno = new ArrayList<LoteDTO>();
			lotes.forEach(lote -> retorno.add(LoteDTO.fromCompleteLote(lote)));
			
			return new ResponseEntity<List<LoteDTO>>(retorno, HttpStatus.OK);	
		}
	}
	
	@RequestMapping(value = "/admin/lote/faltando", method = RequestMethod.GET)
	@ApiOperation(value="Lista todos os lotes do sistema, que estão com baixa quantidade de itens.")
	public ResponseEntity<List<LoteDTO>> listAllLotesEmFalta() {
		List<Lote> lotes = loteService.findAllLotesEmFalta();

		if (lotes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			List<LoteDTO> retorno = new ArrayList<LoteDTO>();
			lotes.forEach(lote -> retorno.add(LoteDTO.fromCompleteLote(lote)));
			
			return new ResponseEntity<List<LoteDTO>>(retorno, HttpStatus.OK);	
		}
	}
	
	@RequestMapping(value = "/admin/lote/validade", method = RequestMethod.GET)
	@ApiOperation(value="Lista todos os lotes do sistema, que estão próximos de se vencerem.")
	public ResponseEntity<List<LoteDTO>> listAllLotesVencendo() {
		List<Lote> lotes = loteService.findAllLotesVencendo();

		if (lotes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			List<LoteDTO> retorno = new ArrayList<LoteDTO>();
			lotes.forEach(lote -> retorno.add(LoteDTO.fromCompleteLote(lote)));
			
			return new ResponseEntity<List<LoteDTO>>(retorno, HttpStatus.OK);	
		}
	}
}
