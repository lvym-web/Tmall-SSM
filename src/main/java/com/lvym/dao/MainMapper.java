package com.lvym.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lvym.beans.Category;
import com.lvym.beans.Order;
import com.lvym.beans.Orderitem;
import com.lvym.beans.Product;
import com.lvym.beans.Productimage;
import com.lvym.beans.Review;

public interface MainMapper {

	List<Product> getForesearch(String keyword);

	List<Product> getForeproduct(Integer id);

	Product getProduct(Integer id);

	Review getReview(Integer id);

	List<Productimage> getProductimages(Integer id);

	List<Orderitem> getOrderItem(Integer id);

	void addShoppingCart(Orderitem orderitem);

	void update_cart(Orderitem orderitem);

	List<Category> getForecategory(Integer cid);

	Category getCategory(Integer cid);

	List<Product> getProducts(Integer cid);

	void updateOderItem(@Param("pid")Integer pid,@Param("uid")Integer uid,@Param("number")Integer number);

	void addForebuyone(@Param("pid")Integer pid,@Param("uid")Integer uid,@Param("number")Integer number);

	void updateOderItem(Orderitem oi);

	void addForebuyone(Orderitem oi);

	Orderitem getOrderItems(@Param("id")Integer id,@Param("uid")Integer uid);

	Orderitem getOrderitem(int id);

	float add(Order order, List<Orderitem> ois);

	void addOrder(Order order);

	void updateOrderitem(Orderitem oi);

	Order getPayed(Integer oid);

	Orderitem getOrderitemByOne(@Param("pid")Integer pid,@Param("id")Integer id);

	void updateShopCart(Orderitem orderitem);

	void addShopCart(Orderitem oi);

	List<Orderitem> getCartOrderitem(Integer uid);

	void updateOrder(Integer oid);

	List<Order> getBought(@Param("id")Integer id,@Param("oid")Integer oid);

	int getOrderitemcart(Orderitem orderitem);

	void updateOderItemStatus(Integer oid);

	void updateOrderHide(Integer oid);

	List<Order> getWaitsBought(@Param("id")Integer id,@Param("status")String status);

	List<Order> getBoughtop(Integer uid);

	List<Order> getOrderConfirmPay(Integer oid);

	List<Order> getOrferReview(Integer oid);

	void updateOrderConfirm(Integer oid);

	

	

	


	

}
