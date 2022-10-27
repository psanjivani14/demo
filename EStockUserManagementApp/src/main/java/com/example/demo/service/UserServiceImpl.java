package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> getAllUser() {
		List<User> userLst = userRepo.findAll();
		if(userLst!=null && !userLst.isEmpty()) {
			return userLst;
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		userRepo.deleteById(userId);
		return true;
	}

	@Override
	public User addUser(User user) {
		if(user!=null) {
			return userRepo.saveAndFlush(user);
		}
		return null;
	}

	@Override
	public boolean validateUser(String username, String password) {
		User user = userRepo.validateUser(username, password);
		if(user!=null) {
			return true;
		}
		return false;
	}
	
	
    
}
