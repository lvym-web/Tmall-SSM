package com.lvym.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lvym.beans.Category;
import com.lvym.beans.Order;
import com.lvym.beans.Product;
import com.lvym.beans.Productimage;
import com.lvym.beans.Property;
import com.lvym.beans.Propertyvalue;

public interface AdminMapper {

	List<Category> getAdmin_category_list();

	List<Property> getAdmin_property_list(Integer cid);

	List<Product> getAdmin_product_list(Integer cid);

	Category getAdmin_category_edit(Integer id);

	int deleteAdmin_category_delete(Integer id);

	void updateCategoryImages(@Param("no")int no,@Param("id")Integer id,@Param("name")String name);

	void updateCategoryImage(@Param("newNo")int newNo,@Param("id")Integer id,@Param("name")String name);

	Category getCategory();

	void addAdmin_category_add(String name);

	Property getAdmin_property_edit(Integer id);

	int deleteAdmin_property_delete(Integer id);

	void addAdmin_property_add(@Param("name")String name,@Param("cid")Integer cid);

	void updateAdmin_property_update(@Param("id")Integer id,@Param("name")String name);

	List<Productimage> getAdmin_productImage_list(Integer pid);

	List<Propertyvalue> getAdmin_propertyValue_edit(Integer pid);

	Product getAdmin_product_edit(Integer id);

	int deleteAdmin_product_delete(Integer id);

	List<Productimage> getAdmin_productImage_lists(Integer pid);

	Productimage getProductimageID();

	void addAdmin_productImage_add(@Param("type")String type,@Param("pid")Integer pid);

	int deleteAdmin_productImage_delete(Integer id);

	void addAdmin_product_add(Product product);

	void updateAdmin_product_update(Product product);

	Product getProductID(Integer pid);

	List<Order> getAdmin_order_list();

	void updateAdmin_order(Integer id);

}
