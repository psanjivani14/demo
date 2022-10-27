package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumeController {


	@PostMapping("/consume-register")
	public ResponseEntity<?> consumeRegister() throws Exception{
		
		RestTemplate restTemp = new RestTemplate();
		
		String baseUrl="http://localhost:8081/auth/user/register-user";
		
		ResponseEntity<String> response = null;
		
		try {
			//response = restTemp.exchange(baseUrl, HttpMethod.GET)
			response = restTemp.exchange(baseUrl, HttpMethod.POST, getHeader(), String.class);
			System.out.println("Response:::: "+response.getBody());
			System.out.println("Headers:::: "+response.getHeaders());
			System.out.println("Status Code:::: "+response.getStatusCodeValue());
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
		
	} 
	
	@PostMapping("/consume-login")
	public ResponseEntity<?> consumeLogin() throws Exception{
		
		RestTemplate restTemp = new RestTemplate();
		
		String baseUrl="http://localhost:8081/auth/user/login";
		
		ResponseEntity<String> response = null;
		
		try {
			//response = restTemp.exchange(baseUrl, HttpMethod.GET)
			response = restTemp.exchange(baseUrl, HttpMethod.POST, getHeader(), String.class);
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
