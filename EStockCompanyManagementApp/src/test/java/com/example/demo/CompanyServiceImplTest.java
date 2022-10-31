package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceImplTest {
	
	@Mock
	private CompanyRepository companyRepo;
	
	@InjectMocks
	private CompanyServiceImpl compServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(compServiceImpl).build();
	}

	List<Company> companyList = new ArrayList<>();
	Company comp1 = new Company();
	@Test
	public void getAllCompanyDtlsSuccess() throws Exception {
		
				Company company = new Company();
				company.setCompanyCode(101);
				company.setCompanyName("Wipro");
				
				companyList.add(company);
				when(companyRepo.findAll()).thenReturn(companyList);
				
				List<Company> compLst = compServiceImpl.getAllCompanyDtl(); 
				assertEquals(companyList, compLst);
				
	}
	@Test
	public void getAllCompanyDtlsFailure() throws Exception {
		
		when(companyRepo.findAll()).thenReturn(null);
		
		List<Company> compLst = compServiceImpl.getAllCompanyDtl(); 
		assertNull(compLst);
		
     }
	
	@Test
	public void addCompanySuccess() throws Exception {
		
				Company company = new Company();
				company.setCompanyCode(101);
				company.setCompanyName("Wipro");
				
				//companyList.add(company);
				when(companyRepo.save(any())).thenReturn(company);
				
				Company c1= compServiceImpl.addCompany(company);
				assertEquals(company, c1);
				
	}
	@Test
	public void deleteCompanySuccess() throws Exception{
		Company comp = new Company();
		comp.setCompanyCode(900);
		comp.setCompanyName("MI");
		comp.setCompanyCeo("Nishant");
		comp.setTurnover(10000001L);
		
		boolean flag = compServiceImpl.deleteCompany(comp.getCompanyCode());
		assertFalse(flag);
		
	}
	
	@Test
	public void deleteCompanyFailure() throws Exception{
		Company comp = new Company();
		//comp.setCompanyCode(900);
		comp.setCompanyName("MI");
		comp.setCompanyCeo("Nishant");
		comp.setTurnover(10000001L);
		
		boolean flag = compServiceImpl.deleteCompany(comp.getCompanyCode());
		assertFalse(flag);
		
	}
	
	@Test
	public void updateCompanySuccess() throws Exception{
		Company comp = new Company();
		comp.setCompanyCode(900);
		comp.setCompanyName("MI");
		comp.setCompanyCeo("Sanjivani");
		comp.setTurnover(10000001L);
		
		when(companyRepo.save(any())).thenReturn(true);
		boolean up = compServiceImpl.updateCompany(comp);
		assertFalse(up);
		
	}
	
	@Test
	public void updateCompanyFailure() throws Exception{
		Company comp = new Company();
		comp.setCompanyCode(900);
		comp.setCompanyName("MI");
		comp.setCompanyCeo("Sanjivani");
		comp.setTurnover(10000001L);
		
		when(companyRepo.save(any())).thenReturn(null);
		boolean up = compServiceImpl.updateCompany(comp);
		assertFalse(up);
		
	}
	
	/*@Test
	public void addCompanyFailure() throws Exception{
		
		when(companyRepo.save(any())).thenReturn(null);
		int code = comp1.setCompanyCode((Integer) null);
		comp1.setCompanyCode(code);
		Company c1= compServiceImpl.addCompany(comp1);
		assertNull(c1);
	}*/
	
}
