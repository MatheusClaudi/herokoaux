package com.ufcg.psoft.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
	List<Produto> findProdutoByNomeAndFabricante(String nome, String fabricante);
	
	@Query (value = "SELECT p FROM Produto p ORDER BY p.nome")
	List<Produto> getProdutoByNome();

	@Query(value = "SELECT p FROM Produto p ORDER BY p.categoria")
	List<Produto> getProdutoByCategoria();

	@Query(value = "SELECT p FROM Produto p ORDER BY p.preco")
	List<Produto> getProdutoByPreco();
	
}
