package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.CompanyController;
import com.example.demo.model.Company;
import com.example.demo.service.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest {
	
 @Mock
 private CompanyServiceImpl comServiceImpl;
 
 @Mock
 private CompanyController comController;
 
 @Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(comController).build();
	}
 
	List<Company> companyList = new ArrayList<>();
	@Test
	public void getAllCompanyDtlsSuccess() throws Exception {
		
				Company company = new Company();
				company.setCompanyCode(101);
				company.setCompanyName("Wipro");
				
				companyList.add(company);
				when(comServiceImpl.getAllCompanyDtl()).thenReturn(companyList);
				
				List<Company> compLst = comServiceImpl.getAllCompanyDtl(); 
				assertEquals(companyList, compLst);
				
				mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getAllCompanyDtl").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
				
	}
	
	@Test
	public void getAllCompanyDtlsFailure() throws Exception{
		
		companyList.clear();
		when(comServiceImpl.getAllCompanyDtl()).thenReturn(companyList);
		
		System.out.println("company List:: "+companyList.size());
		assertEquals(0, companyList.size());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getAllCompanyDtl").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	/// add company api's
	@Test
	public void addCompanySuccess() throws Exception {
		
				Company company = new Company();
				company.setCompanyCode(101);
				company.setCompanyName("Wipro");
				
				companyList.add(company);
				when(comServiceImpl.addCompany(any())).thenReturn(company);
				
				assertEquals(1, companyList.size());
				
				mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/addCompany").contentType(MediaType.APPLICATION_JSON)
						.contentType(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isCreated());
				
	}
	
	@Test
	public void addCompanyFailure() throws Exception{
		
		when(comServiceImpl.addCompany(any())).thenReturn(null);
		
		Company com =comServiceImpl.addCompany(null);
		assertNull(com);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/addCompany").contentType(MediaType.APPLICATION_JSON)
				.contentType(new ObjectMapper().writeValueAsString(com))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
 
 // delete company dtl api's
	@Test
	public void deleteCompanySuccess() throws Exception {
		
		
	//	when(comServiceImpl.deleteCompany(any())).thenReturn();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/deleteCompany/{compId}").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
