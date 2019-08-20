package com.lvym.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvym.beans.Category;
import com.lvym.beans.Product;
import com.lvym.beans.User;
import com.lvym.dao.UserMapper;


@Service
public class UserService {

	@Autowired
	private UserMapper um;
	

	public void addUser(String name,String password) {
		um.addUser(name,password);
		
	}


	public User getForelogin(User user) {
	
		return um.getForelogin(user);
	}


	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return um.getAllUser();
	}


	public List<Category> getAllCategory() {
		
		return um.getAllCategory();
	}


	public List<Product> getProductsCategorys() {
		
		return um.getProductsCategorys();
	}


	


	
	
	
	
}
