package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@RestController
public class ConsumerController {
	
	@GetMapping("/comsume-company")
	public ResponseEntity<?> consumeGetCompany() throws Exception{
		
		RestTemplate restTemp = new RestTemplate();
		
		String baseUrl="http://localhost:8082/api/v1.0/market/company/getAllCompanyDtl";
		
		ResponseEntity<String> response = null;
		
		try {
			//response = restTemp.exchange(baseUrl, HttpMethod.GET)
			response = restTemp.exchange(baseUrl, HttpMethod.GET, getHeader(), String.class);
			System.out.println("Response:::: "+response.getBody());
			System.out.println("Headers:::: "+response.getHeaders());
			System.out.println("Status Code:::: "+response.getStatusCodeValue());
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
		
	} 
	
	public static HttpEntity<?> getHeader() throws Exception
	{
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(httpHeader);
		
	}

}
