package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.CompanyServiceImpl;
import com.example.demo.service.StockServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepo;
	
	@InjectMocks
	private StockServiceImpl stockServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(stockServiceImpl).build();
	}

	Set<Stock> stockList = new HashSet<>();
	Stock stock = null;
	@Test
	public void getAllStockDtlsSuccess() throws Exception {
		
				stock = new Stock();
				//Stock stock = new Stock();
				stock.setComp_code_fk(201);
				stock.setStockPrice(1200);
				
				stockList.add(stock);
				//List<Object> lst = Arrays.asList(stockList);
				//when(stockRepo.findAll()).thenReturn(lst);
				
				//Set<Stock> stockk = stockServiceImpl.getAllStock(stock.getComp_code_fk()); 
				//assertEquals(stockList, stockk);
				
	}
	@Test
	public void getAllStockDtlsFailure() throws Exception {
		
		when(stockRepo.findAll()).thenReturn(null);
		
		List<Stock> stockLst = (List<Stock>) stockServiceImpl.getAllStock(stock.getComp_code_fk()); 
		assertNull(stockLst);
		
     }
	
	@Test
	public void addStockSuccess() throws Exception {
		
				Stock stock = new Stock();
				stock.setComp_code_fk(101);
				stock.setStockPrice(1200);
				
				//companyList.add(company);
				when(stockRepo.save(any())).thenReturn(stock);
				
				boolean c1= stockServiceImpl.addstock(stock);
				assertTrue(c1);
	}
	
	@Test
	public void addStockFailure() {
		
		stockList.clear();
		when(stockRepo.save(any())).thenReturn(stock);
		boolean c1 = stockServiceImpl.addstock(null);
		assertFalse(c1);
	}

}
