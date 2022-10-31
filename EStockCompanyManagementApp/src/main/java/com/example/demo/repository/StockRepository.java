package com.example.demo.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Stock;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Integer>{
	
	@Query(value="select s.* from Stock s where s.comp_code_fk= :comp_code_fk ", nativeQuery = true)
	public Set<Stock> getStockList( int comp_code_fk);
	
	@Modifying
	@Query(value="delete from Stock where comp_code_fk= :comp_code_fk ")
	public void deleteStockData( int comp_code_fk);

}