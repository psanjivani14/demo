package com.example.demo.service;

import java.util.Set;

import com.example.demo.model.Stock;

public interface StockService {
	
	public Set<Stock> getAllStock(int compId);
	
	public boolean deleteStock(int compId);
	
	public boolean addstock(Stock stock);

}