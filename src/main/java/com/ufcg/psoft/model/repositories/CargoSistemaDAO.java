package com.ufcg.psoft.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ufcg.psoft.model.CargoSistema;

@NoRepositoryBean
public interface CargoSistemaDAO extends JpaRepository<CargoSistema, Long> {

}
