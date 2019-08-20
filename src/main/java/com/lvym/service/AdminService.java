package com.lvym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvym.beans.Category;
import com.lvym.beans.Order;
import com.lvym.beans.Product;
import com.lvym.beans.Productimage;
import com.lvym.beans.Property;
import com.lvym.beans.Propertyvalue;
import com.lvym.dao.AdminMapper;
@Service
public class AdminService {
	
	@Autowired
	private AdminMapper am;
	

	public List<Category> getAdmin_category_list() {
	
		return am.getAdmin_category_list();
	}


	public List<Property> getAdmin_property_list(Integer cid) {
		
		return am.getAdmin_property_list(cid);
	}


	public List<Product> getAdmin_product_list(Integer cid) {
		
		return am.getAdmin_product_list(cid);
	}


	public Category getAdmin_category_edit(Integer id) {
		
		return am.getAdmin_category_edit(id);
	}


	public int deleteAdmin_category_delete(Integer id) {
		
		return am.deleteAdmin_category_delete(id);
	}



	public void updateCategoryImages(int no, Integer id, String name) {
		am.updateCategoryImages(no,id,name);
		
	}



	public void updateCategoryImage(int newNo, Integer id,String name) {
		am.updateCategoryImage(newNo,id,name);
		
	}


	public Category getCategory() {
		
		return am.getCategory();
	}


	public void addAdmin_category_add(String name) {
		am.addAdmin_category_add(name);
		
	}


	public Property getAdmin_property_edit(Integer id) {
		
		return am.getAdmin_property_edit(id);
	}


	public int deleteAdmin_property_delete(Integer id) {
		
		return am.deleteAdmin_property_delete(id);
	}


	public void addAdmin_property_add(String name, Integer cid) {
		am.addAdmin_property_add(name,cid);
	}


	public void updateAdmin_property_update(Integer id, String name) {
		am.updateAdmin_property_update(id,name);
		
	}


	public List<Productimage> getAdmin_productImage_list(Integer pid) {
		
		return am. getAdmin_productImage_list(pid);
	}


	public List<Propertyvalue> getAdmin_propertyValue_edit(Integer pid) {
		
		return am.getAdmin_propertyValue_edit(pid);
	}


	public Product getAdmin_product_edit(Integer id) {
		
		return am.getAdmin_product_edit(id);
	}


	public int deleteAdmin_product_delete(Integer id) {
		
		return am.deleteAdmin_product_delete(id);
	}


	public List<Productimage> getAdmin_productImage_lists(Integer pid) {
		
		return am.getAdmin_productImage_lists(pid);
	}


	public Productimage getProductimageID() {
		
		return am.getProductimageID();
	}


	public void addAdmin_productImage_add(String type, Integer pid) {
		am.addAdmin_productImage_add(type,pid);
		
	}


	public int deleteAdmin_productImage_delete(Integer id) {
	
		return am.deleteAdmin_productImage_delete(id);
	}


	public void addAdmin_product_add(Product product) {
		am.addAdmin_product_add(product);
	}


	public void updateAdmin_product_update(Product product) {
		am.updateAdmin_product_update(product);
		
	}


	public Product getProductID(Integer pid) {
		
		return am.getProductID(pid);
	}


	public List<Order> getAdmin_order_list() {
		
		return am.getAdmin_order_list();
	}


	public void updateAdmin_order(Integer id) {

		am.updateAdmin_order(id);
	}


	
}
