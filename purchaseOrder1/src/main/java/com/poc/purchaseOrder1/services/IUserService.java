package com.poc.purchaseOrder1.services;

import java.util.List;

import com.poc.purchaseOrder1.models.User;

public interface IUserService {
	
	public List<User> getAllUsers();
	
	public User getUserById(Integer id);
	
	public User saveUser(User u);
	
	public int  deleteUser(User u);
}
