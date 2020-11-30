package com.ufcg.psoft.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.Cliente;

@Repository
public interface ClienteDAO extends CargoSistemaDAO {

}
