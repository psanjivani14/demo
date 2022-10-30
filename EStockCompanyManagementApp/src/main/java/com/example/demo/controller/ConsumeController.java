package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;

@RestController
public class ConsumeController {
	

	@PostMapping("/consume-register")
	public ResponseEntity<?> consumeRegister(@RequestBody User user) throws Exception{
		
		String baseUrl="http://localhost:8081/auth/user/register-user";
		RestTemplate restTemp = new RestTemplate();
		String response = null;
		
		try {
			System.out.println("Inside try:::baseUrl is:: "+baseUrl);
			response =restTemp.postForObject(baseUrl, user, String.class);
			System.out.println("Response:::: "+response);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
		
	} 
	
	@PostMapping("/consume-login")
	public ResponseEntity<?> consumeLogin(@RequestBody User user) throws Exception{
		
		RestTemplate restTemp = new RestTemplate();
		String baseUrl="http://localhost:8081/auth/user/login";
		String response = null;
		
		try {
			response =restTemp.postForObject(baseUrl, user, String.class);
			System.out.println("Response:::: "+response.toString());
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
