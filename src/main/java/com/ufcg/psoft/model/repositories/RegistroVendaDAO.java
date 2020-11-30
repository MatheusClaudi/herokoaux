package com.ufcg.psoft.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.RegistroVenda;

@Repository
public interface RegistroVendaDAO extends JpaRepository<RegistroVenda, Long> {

    @Query (value = "SELECT p FROM RegistroVenda p ORDER BY p.valorTotal")
    List<RegistroVenda> getRegistroVendaByValorTotal();
}
