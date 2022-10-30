package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.User;
import com.example.demo.responsehandler.CustomResponseHandler;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth/user")
public class AuthenticationController {
	
	private Map<String, String> mapObj = new HashMap<String, String>();
	
	private UserService userService;
	
	@Autowired
	public AuthenticationController(UserService userService) {
		super();
		this.userService = userService;
		
	}
	
	
	@PostMapping("/register-user")
	public ResponseEntity<?> addUser(@RequestBody User user){
		if(userService.addUser(user)!=null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("User data not inserted..", HttpStatus.CONFLICT);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		try {
			String jwtToken = generateToken(user.getUsername(), user.getPassword());
			mapObj.put("message", "User successfully logged In");
			mapObj.put("toekn", jwtToken);
			CustomResponseHandler.generateCustomResponseformat("User successfully logged In",
					HttpStatus.OK, mapObj);
		}catch(Exception ex) {
			mapObj.put("message", "Provide valid credentials");
			mapObj.put("toekn", null);
			CustomResponseHandler.generateCustomResponseformat("Provide valid credentials",
					HttpStatus.UNAUTHORIZED, mapObj);
			//return new ResponseEntity<String>("User credentials are invalid",HttpStatus.UNAUTHORIZED );
		}
	   return new ResponseEntity<>(mapObj, HttpStatus.ACCEPTED);
		
	}


	public String generateToken(String username, String password) throws ValidationException {
		String jwtToken ="";
		if(username== null || password== null) {
			System.err.println("Username and Password can not be null...!!");
			throw new ValidationException("Username and Password can not be null...!!");
		}
		
		boolean flag= userService.validateUser(username, password);
		if(!flag) {
			throw new ValidationException("Username and password is incorrect..!!");
		}else {
			jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+ 3000000))
			.signWith(SignatureAlgorithm.HS256, "mykey").compact();
		}
		
		return jwtToken;
	}
	
}
