package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CompanyCodeAlreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistsException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepo;
	
	
	@Override
	public List<Company> getAllCompanyDtl() {
		
		List<Company> companyLst = companyRepo.findAll();
		if(companyLst!=null && !companyLst.isEmpty()) {
			System.out.println("companyLst:  "+companyLst.toString());
			return companyLst;
		}
		return null;
	}

	@Override
	public Company addCompany(Company company) throws CompanyCodeAlreadyExistsException {
		Optional<Company> opObj= companyRepo.findById(company.getCompanyCode());
		if(opObj.isPresent()) {
			throw new CompanyCodeAlreadyExistsException();
		}
		companyRepo.saveAndFlush(company);
		return company;
	}

	@Override
	public boolean deleteCompany(int compCode) {
		companyRepo.deleteById(compCode);
		return true;
	}

	@Override
	public boolean updateCompany(Company company) {
		Company comp1= companyRepo.getById(company.getCompanyCode());
		if(comp1!=null) {
			comp1.setCompanyCeo(company.getCompanyCeo());
			comp1.setCompanyName(company.getCompanyName());
			companyRepo.saveAndFlush(comp1);
			return true;
		}
		return false;
	}

	@Override
	public Company getCompanyByCode(int compCode) throws CompanyNotExistsException {
		Optional<Company> opObj= companyRepo.findById(compCode);
		if(opObj.isPresent()) {
			return opObj.get();
		}
		throw new CompanyNotExistsException();
	}

}
