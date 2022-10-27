package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue
	private int tranactionId;
	
	private String stockPrice;
	
	Date issueAt;
	
	private int comp_code_fk;

	public int getTranactionId() {
		return tranactionId;
	}

	public void setTranactionId(int tranactionId) {
		this.tranactionId = tranactionId;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Date getIssueAt() {
		return issueAt;
	}

	public void setIssueAt(Date issueAt) {
		this.issueAt = issueAt;
	}

	public int getComp_code_fk() {
		return comp_code_fk;
	}

	public void setComp_code_fk(int comp_code_fk) {
		this.comp_code_fk = comp_code_fk;
	}
	
	@PrePersist
	public void setTimeStamp() {
		if(this.issueAt == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		}
		
	}
	
	
	

}
