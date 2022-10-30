package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController() {
		System.out.println("User controller created successfully");
	}
	
	@PostMapping("/add-user")
	public ResponseEntity<?> addUser(@RequestBody User user){
		System.out.println("Inside addUser controller :: "+user);
		User user1 = userService.addUser(user);
		if(user1!=null) {
			return new ResponseEntity<String>("User created successfully..!!", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("User not added successfully ", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUser(){
		List<User> userList = userService.getAllUser();
		if(userList!=null) {
			return new ResponseEntity<Object>(userList, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Not able to retrived user data", HttpStatus.CONFLICT);
		
	}
}
