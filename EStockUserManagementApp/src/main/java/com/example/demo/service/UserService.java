package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	
	public List<User> getAllUser();
	
	public boolean deleteUser(int userId);
	
	public User addUser(User user);
	
	public boolean validateUser(String username, String password);
	

}
