package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	
	@Id
	@NotNull(message = "Company code should not be null")
	private int companyCode;
	@NotNull(message = "Company Name should not be null")
	private String companyName;
	@NotNull(message = "Company website should not be null")
	private String website;
	@NotNull(message = "Company turnover should not be null")
	@Min(10000001)
	private Long turnover;
	@NotNull(message = "Company ceo name should not be null")
	private String companyCeo;
	@NotNull(message = "Company stockPrice should not be null")
	private double stockPrice;
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	@JsonIgnore
	@OneToMany(targetEntity = Stock.class)
	private Set<Stock> stockList;
	
	
	public String setCompanyName(String companyName) {
		this.companyName = companyName;
		return companyName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {		
		this.website = website;
	}
	public Long getTurnover() {
		return turnover;
	}
	public void setTurnover(Long turnover) {
		this.turnover = turnover;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	
	public Set<Stock> getStockList() {
		return stockList;
	}
	public void setStockList(Set<Stock> stockList) {
		this.stockList = stockList;
	}
	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", companyName=" + companyName + ", website=" + website
				+ ", turnover=" + turnover + ", companyCeo=" + companyCeo + ", stockPrice=" + stockPrice
				+ ", stockList=" + stockList + "]";
	}
	public Company(int companyCode, String companyName, String website, Long turnover, String companyCeo) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.website = website;
		this.turnover = turnover;
		this.companyCeo = companyCeo;
		
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}