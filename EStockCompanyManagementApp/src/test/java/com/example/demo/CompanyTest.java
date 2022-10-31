package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.model.Company;

public class CompanyTest {
	
	@Test
	public void test01() {
		
		Company company = Mockito.mock(Company.class); ///created here mock object
		
		when(company.getCompanyName()).thenReturn(null);
		
		Company comp = new Company();
		
		String settingName = comp.setCompanyName("TCS"); //setting mock obj
		
		String name = comp.getCompanyName();
		System.out.println(settingName);
		when(company.getCompanyName()).thenReturn(settingName);
		
		assertEquals(settingName, name);
	}
	
	@Test
	public void test02() {
		
		Company company = Mockito.mock(Company.class); ///created here mock object
		
		when(company.getCompanyCeo()).thenReturn(null);
		
		Company comp = new Company();
		
		String compceo = comp.setCompanyCeo("Parag"); //setting mock obj
		
		String ceo = comp.getCompanyCeo();
		System.out.println(compceo);
		when(company.getCompanyCeo()).thenReturn(compceo);
		
		assertEquals(compceo, ceo);
	}
	
	@Test
	public void test03() {
		
		Company company = Mockito.mock(Company.class); ///created here mock object
		
		when(company.getCompanyCode()).thenReturn(0);
		
		Company comp = new Company();
		
		int compcode = comp.setCompanyCode(101); //setting mock obj
		
		int  code = comp.getCompanyCode();
		System.out.println(compcode);
		when(company.getCompanyCode()).thenReturn(compcode);
		
		assertEquals(compcode, code);
	}
	

}
