package com.ufcg.psoft.service;

import java.util.List;

import com.ufcg.psoft.model.RegistroVenda;

public interface RegistroVendaService {
	
	List<RegistroVenda> findAll();
	
	RegistroVenda findRegistroVendaById(long id) throws Exception;
	
	RegistroVenda saveRegistroVenda(RegistroVenda registro) throws Exception;
	
	void deleteRegistroVendaById(long id) throws Exception; 

	List<RegistroVenda> findAllRegistroVenda(String order);
}
