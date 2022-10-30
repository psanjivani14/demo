package com.example.demo.controller;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.CompanyNotExistsException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.responsehandler.MyCustomResponse;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/v1.0/market/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private CompanyService compService;
	
	@PostMapping("/addStock/{compId}")
	public ResponseEntity<?> addstock(@PathVariable ("compId") int compId, 
			@RequestBody Stock stock) throws CompanyNotExistsException{
		Company compexists = compService.getCompanyByCode(compId);
		if(compexists!=null) {
			
			compexists.setStockPrice(stock.getStockPrice());
			stock.setStockPrice(stock.getStockPrice());
			stock.setIssueAt(stock.getIssueAt());
			stock.setComp_code_fk(stock.getComp_code_fk());
			
			if(compService.updateCompany(compexists) && stockService.addstock(stock)) {
				return new ResponseEntity<String>("Stock details saved and updated in Stock and Company table respectively...", HttpStatus.CREATED);
				
			}
		}
		return new ResponseEntity<String>("Company stock data can not be persist", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/getAllStocks/{compid}")
	public ResponseEntity<?> getAllStock(@PathVariable ("compid") int compid){
		Set<Stock> stocklist=  stockService.getAllStock(compid);
		if(stocklist!=null && !stocklist.isEmpty()) {
			CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
			 return MyCustomResponse.generateCustomResponseformat("Successfully retrived stock data",
					HttpStatus.OK, stocklist);
		}
		return new ResponseEntity<String>("Stock not available", HttpStatus.NO_CONTENT);
		
	}

}