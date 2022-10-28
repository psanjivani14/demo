package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	
	@Id
	private int companyCode;
	private String companyName;
	private String website;
	private Long turnover;
	private String companyCeo;
	private String stockPrice;
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(String stockPrice) {
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