package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.CompanyCodeAlreadyExistsException;
import com.example.demo.model.Company;

public interface CompanyService {
	
	public List<Company> getAllCompanyDtl();
	public Company addCompany(Company company) throws CompanyCodeAlreadyExistsException;
	public boolean deleteCompany(int compCode);
	public boolean updateCompany(Company company);
	public Company getCompanyByCode(int compCode);

}
