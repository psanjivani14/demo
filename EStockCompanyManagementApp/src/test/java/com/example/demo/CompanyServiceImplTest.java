package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
	
}
