package com.ufcg.psoft.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.ItemRegistroVenda;

@Repository
public interface ItemRegistroVendaDAO extends JpaRepository<ItemRegistroVenda, Long> {

}
