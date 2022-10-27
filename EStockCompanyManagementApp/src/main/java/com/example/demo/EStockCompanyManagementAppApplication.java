package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.jwtfilter.SecurityFilter;

@SpringBootApplication
public class EStockCompanyManagementAppApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean obj = new FilterRegistrationBean();
		obj.setFilter(new SecurityFilter());
		obj.addUrlPatterns("/api/v1.0/market/company/*");
		return obj;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Main method started");
		SpringApplication.run(EStockCompanyManagementAppApplication.class, args);
	}

}
