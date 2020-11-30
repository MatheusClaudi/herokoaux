package com.ufcg.psoft.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.ItemRegistroVenda;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.RegistroVenda;
import com.ufcg.psoft.model.DTO.ItemRegistroVendaDTO;
import com.ufcg.psoft.model.DTO.LoteDTO;
import com.ufcg.psoft.model.DTO.RegistroVendaDTO;
import com.ufcg.psoft.service.InventarioService;
import com.ufcg.psoft.service.InventarioServiceImpl;
import com.ufcg.psoft.service.LoteService;
import com.ufcg.psoft.service.LoteServiceImpl;
import com.ufcg.psoft.service.ProdutoService;
import com.ufcg.psoft.service.ProdutoServiceImpl;
import com.ufcg.psoft.service.RegistroVendaService;
import com.ufcg.psoft.service.RegistroVendaServiceImpl;
import com.ufcg.psoft.util.CustomErrorType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api("API REST Registro Vendas")
@CrossOrigin
public class RegistroVendaController {
	
	@Autowired
	RegistroVendaService registroVendaService = new RegistroVendaServiceImpl();
	
	@Autowired
	ProdutoService produtoService = new ProdutoServiceImpl();
	
	@Autowired
	InventarioService inventarioService = new InventarioServiceImpl();
	
	
	@RequestMapping(value = "/admin/registroVenda", method = RequestMethod.GET)
	@ApiOperation(value="Lista todos os registros de venda do sistema, podendo ou nao ser ordenado a depender do parametro order, por exemplo order pode assumir valorTotal.")
	public ResponseEntity<List<RegistroVendaDTO>> listRegistroVenda(@RequestParam (required = false, defaultValue = "none") String order) {
		List<RegistroVenda> registros = registroVendaService.findAllRegistroVenda(order);

		if (registros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			List<RegistroVendaDTO> resposta = new ArrayList<RegistroVendaDTO>();
			registros.forEach(registro -> {resposta.add(RegistroVendaDTO.fromCompleteRegistroVenda(registro));});
			return new ResponseEntity<List<RegistroVendaDTO>>(resposta, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/admin/registroVenda", method = RequestMethod.POST)
	@ApiOperation(value="Realiza o registro de uma venda, modificando os dados de estoque.")
	public ResponseEntity<?> regitrarVenda(@RequestBody RegistroVendaDTO registroVendaDTO) {
				
		try {
			RegistroVenda registroCriado = this.inventarioService.registraVendaProdutos(registroVendaDTO);
			RegistroVendaDTO resposta = RegistroVendaDTO.fromCompleteRegistroVenda(registroCriado);
			
			return new ResponseEntity<>(resposta, HttpStatus.CREATED);
		}catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
	}

	
	@RequestMapping(value = "/admin/registroVenda/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="Cancela o registro de uma venda, modificando os dados de estoque.")
	public ResponseEntity<?> deleteVenda(@PathVariable("id") long id) {
		try {
			this.registroVendaService.deleteRegistroVendaById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
		}
	}
}
