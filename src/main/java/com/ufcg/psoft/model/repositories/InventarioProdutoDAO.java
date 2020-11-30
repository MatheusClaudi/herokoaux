package com.ufcg.psoft.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.Lote;

@Repository
public interface InventarioProdutoDAO extends JpaRepository<InventarioProduto, Long> {

}
