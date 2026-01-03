package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public String userResponse(String name) {
		return "Hello " + name+ " Welcome . Your Role is User";
	}
	
	public String adminResponse(String name) {
		return "Hello " + name+ " Welcome . Your Role is Admin";
	}
	
	public String getUserRole(String username, String password) {
		User ref = userRepo.findByUsername(username);
		
		if(ref != null) {
			if(ref.getPassword().equals(password)) {
				return ref.getRole();
			}
			else {
				return "invalid";
			}
		}
		return "incorrect";
	}
}
