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
	
	@Query(value="select s from Stock s where s.comp_code_fk= :compId ")
	public Set<Stock> getStockList(int compId);
	
	@Modifying
	@Query(value="delete from Stock where comp_code_fk = :compId ")
	public void deleteStockData(int compId);

}
