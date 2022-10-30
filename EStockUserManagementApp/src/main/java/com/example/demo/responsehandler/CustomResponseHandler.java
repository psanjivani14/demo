package com.example.demo.responsehandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseHandler {

	public static ResponseEntity<Object> generateCustomResponseformat(String message, 
			HttpStatus status, Object responseonj){
		Map<String, Object> map = new HashMap<>();
		map.put("Message", message);
		map.put("Status", status);
		map.put("Data", responseonj);
		
		return new ResponseEntity<Object>(map, status);
		
	}
}
