package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{

	@Autowired
	private StockRepository stockRepo;
	
	@Override
	public Set<Stock> getAllStock(int compId) {
		Set<Stock> stockLst= stockRepo.getStockList(compId);
		return stockLst;
	}

	@Override
	public boolean deleteStock(int compId) {
		stockRepo.deleteStockData(compId);
		return true;
	}

	@Override
	public boolean addstock(Stock stock) {
		Stock stock1= new Stock();
		stock1.setStockName(stock.getStockName());
		stock1.setComp_code_fk(stock.getComp_code_fk());
		stock1.setIssueAt(stock.getIssueAt());
		stockRepo.saveAndFlush(stock1);
		return true;
	}

}
