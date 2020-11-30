package com.ufcg.psoft.service;

import java.util.List;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.Produto;

public interface CategoriaService {
	
	List<Categoria> findAllCategorias();

	Categoria saveCategoria(Categoria cattegoria) throws Exception;

	Categoria findCategoriaById(long id) throws Exception;
	
	Categoria findCategoriaByName(String name) throws Exception;

	void updateCategoria(Categoria categoria) throws Exception;


}
