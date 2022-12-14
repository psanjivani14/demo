package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.CompanyCodeAlreadyExistsException;
import com.example.demo.exceptions.CompanyNotExistsException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.responsehandler.MyCustomResponse;
import com.example.demo.service.CompanyServiceImpl;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/v1.0/market/company")
public class CompanyController {
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("/getAllCompanyDtl")
	public ResponseEntity<?> getAllCompanyDtl(){
		
		List<Company> companyLst = companyService.getAllCompanyDtl();
		if(companyLst!=null && !companyLst.isEmpty()) {
			System.out.println("inside if:: "+companyLst.toString());
			//CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
			//return ResponseEntity.ok().cacheControl(cacheControl).body(MyCustomResponse.generateCustomResponseformat("Successfully retrived company data",
				//	HttpStatus.OK, companyLst));
			for(Company company : companyLst) {
				System.out.println("inside for each:: "+company.toString());
				Set<Stock> stockList = stockService.getAllStock(company.getCompanyCode());
				System.out.println("stockList:: "+stockList);
				company.setStockList(stockList);
			}
			return MyCustomResponse.generateCustomResponseformat("Successfully retrived company data",
						HttpStatus.OK, companyLst);
		}
		return  MyCustomResponse.generateCustomResponseformat("could not retrived company data", HttpStatus.NO_CONTENT, null);
		
	}
	
	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@Valid @RequestBody Company company) throws CompanyCodeAlreadyExistsException{
		System.out.println("Inside addCompany:: "+company.toString());
		if(companyService.addCompany(company)!=null) {
			return new ResponseEntity<String>("Company details stored successfully..!!", HttpStatus.CREATED);
			
		}
		return new ResponseEntity<String>("Company object is null", HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/deleteCompany/{compId}")
	public ResponseEntity<?> deleteCompany(@PathVariable ("compId") int compId) throws CompanyNotExistsException {
		System.out.println("In method deleteCompany1 "+compId);
		if(companyService.deleteCompany(compId) & stockService.deleteStock(compId) ) {
			System.out.println("inside:: deleteCompany::Condition satisfied ");
			return new ResponseEntity<String>("Company data deleted successfully..!!", HttpStatus.OK);
		}
		System.out.println("In method deleteCompany end "+compId);
		return new ResponseEntity<String>("Company data cannot be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PutMapping("/updateCompany")
	public ResponseEntity<?> updatCompany(@RequestBody Company company) throws CompanyNotExistsException{
		if(companyService.updateCompany(company)) {
			return new ResponseEntity<String>("Company details updated successfully.!!", HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("Company could be not updated ", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/getCompanyId/{compId}")
	public ResponseEntity<?> getCompanyByCode(@PathVariable ("compId") int compCode) throws CompanyNotExistsException{
		System.out.println("Inside getCompanyByCode controller: "+compCode);
		Company comp = companyService.getCompanyByCode(compCode);
		if(comp!=null) {
			System.out.println("inside if:: "+comp.toString());
			return MyCustomResponse.generateCustomResponseformat("Successfully retrived company data",
					HttpStatus.OK, comp);
		}
		return new ResponseEntity<String>("Can not fetched company data by using code", HttpStatus.NO_CONTENT);
		
	}
	

}
