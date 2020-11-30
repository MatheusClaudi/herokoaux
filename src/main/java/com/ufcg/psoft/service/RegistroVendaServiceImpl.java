package com.ufcg.psoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.RegistroVenda;
import com.ufcg.psoft.model.repositories.RegistroVendaDAO;

@Service("registroVendaService")
public class RegistroVendaServiceImpl implements RegistroVendaService {

	@Autowired
	private RegistroVendaDAO registroVendaRepository;
	
	@Autowired
	InventarioService inventarioService = new InventarioServiceImpl();
	
	@Override
	public List<RegistroVenda> findAll() {
		return this.registroVendaRepository.findAll();
	}

	@Override
	public RegistroVenda findRegistroVendaById(long id) throws Exception {
		try {
			return this.registroVendaRepository.findById(id).get();
		}
		catch(Exception e)  {
			throw new Exception("Registro Venda com id: " + id +", não está contido no banco de dados");
		}
	}

	@Override
	public RegistroVenda saveRegistroVenda(RegistroVenda registro) throws Exception {
		if (registro.getId() == null) {
			return this.registroVendaRepository.save(registro);
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteRegistroVendaById(long id) throws Exception {
		
		try {
			RegistroVenda rv = this.findRegistroVendaById(id);
			this.inventarioService.reverteRegistroVendaLote(rv.getItensVenda());
			this.registroVendaRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Lote com id: " + id +", não está contido no banco de dados");
		}
		
	}
	
	@Override
	public List<RegistroVenda> findAllRegistroVenda(String order) {
		if(order == null) {
			order="";
		}
		if(order.equals("valorTotal")) {
			return 	registroVendaRepository.getRegistroVendaByValorTotal();
		}
		else {
			return registroVendaRepository.findAll();
		}

	}

}
