package com.ufcg.psoft.model.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;

@Repository
public interface LoteDAO extends JpaRepository<Lote, Long> {
	
	@Query(value="select * from LOTE_TB l where l.numero_de_itens < :numeroDeItens", nativeQuery=true)
	List<Lote> findAllByNumeroDeItens(@Param("numeroDeItens") int numeroDeItens);
	
	@Query(value = "from Lote l where l.dataDeValidade BETWEEN :startDate AND :endDate")
	public List<Lote> getAllBetweenDates(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
	
}
