package com.ufcg.psoft.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ufcg.psoft.model.DescontoCategoria;

@NoRepositoryBean
public interface DescontoCategoriaDAO extends JpaRepository<DescontoCategoria, Long> {

}
