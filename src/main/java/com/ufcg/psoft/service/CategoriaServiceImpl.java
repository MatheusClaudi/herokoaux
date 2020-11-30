package com.ufcg.psoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.repositories.CategoriaDAO;
import com.ufcg.psoft.model.repositories.LoteDAO;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaDAO categoriaRepository;

	@Override
	public List<Categoria> findAllCategorias() {
		return this.categoriaRepository.findAll();
	}

	@Override
	public Categoria saveCategoria(Categoria categoria) throws Exception {
		if (categoria.getId() == null) {
			if (!this.categoriaRepository.findByNomeCategoria(categoria.getNomeCategoria()).isPresent()) {
				return this.categoriaRepository.save(categoria);
			}
			else {
				throw new Exception("Categoria com nome específicado já existe.");
			}
		}
		else {
			return null;
		}
	}
	
	

	@Override
	public Categoria findCategoriaById(long id) throws Exception {
		try {
			return this.categoriaRepository.findById(id).get();
		}
		catch(Exception e)  {
			throw new Exception("Erro ao procurar: Categoria com id: " + id +", não está contido no banco de dados");
		}
	}

	@Override
	public void updateCategoria(Categoria categoria) throws Exception {
		
		long id = categoria.getId();
		
		if (this.categoriaRepository.existsById(id)) {
			this.categoriaRepository.save(categoria);
		}
		else {
			throw new Exception("Erro ao editar: Categoria com id: " + id +", não está contido no banco de dados");
		}
		
	}

	@Override
	public Categoria findCategoriaByName(String name) throws Exception {
		try {
			return this.categoriaRepository.findByNomeCategoria(name).get();
		}
		catch(Exception e)  {
			throw new Exception("Erro ao procurar: Categoria com nome " + name +", não está contido no banco de dados");
		}
	}
	
	

}
