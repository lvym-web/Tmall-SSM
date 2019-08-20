package com.lvym.dao;

import com.lvym.beans.Category;
import com.lvym.beans.Product;
import com.lvym.beans.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
   

	void addUser(@Param("name")String name,@Param("password")String password);

	User getForelogin(User user);

	List<User> getAllUser();

	List<Category> getAllCategory();

	List<Product> getProductsCategorys();
}