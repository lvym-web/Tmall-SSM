package com.lvym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lvym.beans.Category;
import com.lvym.beans.Order;
import com.lvym.beans.Orderitem;
import com.lvym.beans.Product;
import com.lvym.beans.Productimage;
import com.lvym.beans.Review;
import com.lvym.dao.MainMapper;
@Service
public class MainService {

	@Autowired
	private MainMapper mm;
	public List<Product> getForesearch(String keyword) {
		
		return mm.getForesearch(keyword);
	}
	public List<Product> getForeproduct(Integer id) {
		
		return mm.getForeproduct(id);
	}
	
	public Product getProduct(Integer id) {
		
		return mm.getProduct(id);
	}
	public Review getReview(Integer id) {
		
		return mm.getReview(id);
	}
	public List<Productimage> getProductimages(Integer id) {
		
		return mm.getProductimages(id);
	}
	public List<Orderitem> getOrderItem(Integer id) {
		
		return mm.getOrderItem(id);
	}
	
	public List<Category> getForecategory(Integer cid) {
		
		return mm.getForecategory(cid);
	}
	public Category getCategory(Integer cid) {
		
		return mm.getCategory(cid);
	}
	public List<Product> getProducts(Integer cid) {
		
		return mm.getProducts(cid);
	}
	
	

	public void updateOderItem(Orderitem oi) {
		
		mm.updateOderItem(oi);
	}
	public void addForebuyone(Orderitem oi) {
		mm.addForebuyone(oi);
		
	}
	public Orderitem getOrderItems(Integer id, Integer uid) {
		// TODO Auto-generated method stub
		return mm.getOrderItems(id,uid);
	}
	public Orderitem getOrderitem(int id) {
		
		return mm.getOrderitem(id);
	}
	 @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public float OI(Order order, List<Orderitem> ois) {
		 float total = 0;
	       mm.addOrder(order);
	 
	       
	        for (Orderitem oi: ois) {
	            oi.setOid(order.getId());
	            mm.updateOrderitem(oi);
	            total+=oi.getProduct().getPromoteprice()*oi.getNumber();
	            System.out.println(oi);
	        }
	       
	        return total;

	}
	public Order getPayed(Integer oid) {
		
		return mm.getPayed(oid);
	}
	
	
	public Orderitem getOrderitemByOne(Integer pid, Integer id) {
		
		return mm.getOrderitemByOne(pid,id);
	}
	
	public void updateShopCart(Orderitem orderitem) {
		mm.updateShopCart(orderitem);
		
	}
	public void addShopCart(Orderitem oi) {
	mm.addShopCart(oi);
		
	}
	
	public List<Orderitem> getCartOrderitem(Integer uid) {
		
		return mm.getCartOrderitem(uid);
	}
	public void updateOrder(Integer oid) {
		
		mm.updateOrder(oid);
	}
	public List<Order> getBought(Integer id, Integer oid) {
		
		return mm.getBought(id,oid);
	}
	
	public boolean getOrderitemcart(Orderitem orderitem) {

		boolean b=false;
		int i=mm.getOrderitemcart(orderitem);
		if(i>0){
			
			b=true;
		}
		return b;
	}
	public void updateOderItemStatus(Integer oid) {
		mm.updateOderItemStatus(oid);
		
	}
	public void updateOrderHide(Integer oid) {
		mm.updateOrderHide(oid);
		
	}
	public List<Order> getWaitsBought(Integer id, String status) {
		
		return mm.getWaitsBought(id,status);
	}
	public List<Order> getBoughtop(Integer uid) {
	
		return mm.getBoughtop(uid);
	}
	public List<Order> getOrderConfirmPay(Integer oid) {
		
		return mm.getOrderConfirmPay(oid);
	}
	public List<Order> getOrferReview(Integer oid) {
		
		return mm.getOrferReview(oid);
	}
	public void updateOrderConfirm(Integer oid) {
		mm.updateOrderConfirm(oid);
		
	}
	

	

}
