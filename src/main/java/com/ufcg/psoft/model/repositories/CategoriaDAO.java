package com.ufcg.psoft.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.model.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {

	Optional<Categoria> findByNomeCategoria(String nomeCategoria);
}
