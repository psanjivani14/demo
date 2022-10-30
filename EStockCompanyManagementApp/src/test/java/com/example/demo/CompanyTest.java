package com.example.demo;

import static org.junit.Assert.assertFalse;
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
		
		when(company.getCompanyName()).thenReturn(null);
		
		Company comp = new Company();
		
		String settingName = comp.setCompanyName("TCS"); //setting mock obj
		
		String name = comp.getCompanyName();
		System.out.println(settingName);
		when(company.getCompanyName()).thenReturn(settingName);
		
		//assertFalse();
	}

}
