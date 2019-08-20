package com.lvym.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;












import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.ResponseBody;

import com.lvym.beans.Category;
import com.lvym.beans.Order;
import com.lvym.beans.Orderitem;
import com.lvym.beans.Product;
import com.lvym.beans.Productimage;
import com.lvym.beans.Review;
import com.lvym.beans.User;
import com.lvym.service.MainService;
import com.lvym.util.MyJsonUtil;

@Controller
public class MainController {

	@Autowired
	MainService ms;      
	
	@RequestMapping("foresearch")
	public String getForesearch(String keyword,Model model){
		
		List<Product> products=ms.getForesearch(keyword);
		model.addAttribute("products", products);
		return "fore/searchResult";
		
	}
	
	
	@RequestMapping(value="/foreproduct",method=RequestMethod.GET)
	public String getForeproduct(Model model,Integer id,HttpSession session){
		   User user =(User)  session.getAttribute("user");
		List<Product> pc=ms.getForeproduct(id);
	     	model.addAttribute("pc", pc);
		
		Product p=ms.getProduct(id);
		model.addAttribute("product", p);
		List<Productimage> pi=ms.getProductimages(id);
		model.addAttribute("productimage", pi);
		Review r=ms.getReview(id);
		model.addAttribute("review", r);
		Orderitem o=ms.getOrderItems(id,user.getId());
		model.addAttribute("orderitem", o);
				return "fore/product";
	}
	
	@RequestMapping("forecategory")
	public String getForecategory(Integer cid,Model model){
		
		List<Category> categories=ms.getForecategory(cid);
		model.addAttribute("c", categories);
		
		/*Category category=ms.getCategory(cid);
		List<Product> pi=ms.getProducts(cid);
		model.addAttribute("productimage", pi);*/
		
		return "fore/category";
	}
	
	/**
	 *          立即购买
	 * 
	 */
	
	@RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("user");
        if(null!=user){
            return "success";}else{
            	 return "fail";
            }
       
	}
	@RequestMapping("forebuyone")
	public String addForebuyone(Integer pid,Integer num,Model model,HttpSession session){
		
		Product p = ms.getProduct(pid);
        int oiid = 0;
 
        User user =(User)  session.getAttribute("user");
        boolean found = false;
        List<Orderitem> ois = ms.getOrderItem(user.getId());
        
        for (Orderitem oi : ois) {
			if(oi.getPid().intValue()==pid){
								
				
				oi.setNumber(oi.getNumber()+num);
				ms.updateOderItem(oi);
				 found = true;
	                oiid = oi.getId();
	               break;
			}
		}
        if(!found){
            Orderitem oi = new Orderitem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            ms.addForebuyone(oi);
          oiid = oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;
        
    }
      @RequestMapping("forebuy")
      public String buy(Model model,String[] oiid,HttpSession session){
    	  
    	  List<Orderitem> ois = new ArrayList<>();
          float total = 0;
   
          for (String strid : oiid) {
              int id = Integer.parseInt(strid);
              Orderitem oi= ms.getOrderitem(id);
              total +=oi.getProduct().getPromoteprice()*oi.getNumber();
              ois.add(oi);
          }
   
          session.setAttribute("ois", ois);
          model.addAttribute("total", total);
          return "fore/buy";
      }
   
     
	



	@RequestMapping(value="forecreateOrder",method=RequestMethod.POST)
	public String addForecreateOrder(Model model,Order order,HttpSession session){
		 User user =(User)  session.getAttribute("user");
         String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
         order.setOrdercode(orderCode);
         order.setCreatedate(new Date());
         order.setUid(user.getId());
        
         @SuppressWarnings("unchecked")
		List<Orderitem> ois= (List<Orderitem>)  session.getAttribute("ois");
      
         float total =ms.OI(order,ois);
      
         return "redirect:forealipay?oid="+order.getId() +"&total="+total;
	
		
	}
	
	@RequestMapping("forealipay")
	public String getforealipay(Integer oid,float total,Model model){
		model.addAttribute("param", oid);
		model.addAttribute("param", total);
	
		return "fore/alipay";
	}
	
	@RequestMapping("forepayed")
	public String forepayed(Integer oid,float total,Model model){
		
		Order order=ms.getPayed(oid);
		model.addAttribute("o", order);
		
		ms.updateOderItemStatus(oid);
	ms.updateOrder(oid);
		return "fore/payed";
	}
	
	
	
	@RequestMapping("GoShoppingCart")
	public String addCart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			Integer pid,Integer num,HttpSession session,HttpServletRequest request,HttpServletResponse response,Model model){
		
		 User user =(User)  session.getAttribute("user");
		 int oiid=0;
		 Orderitem orderitem=ms.getOrderitemByOne(pid,user.getId());			
		 if(orderitem==null){
			 Orderitem oi=new Orderitem();
			 oi.setPid(pid);
			 oi.setNumber(num);
			 oi.setUid(user.getId());
			 ms.addShopCart(oi);
			 
			oiid=oi.getId();
			
		 }else {
		 orderitem.setNumber(orderitem.getNumber()+num);
			
			ms.updateShopCart(orderitem);
			oiid=orderitem.getId();
			
		}
		return "redirect:forecart?oiid="+oiid+"&uid="+user.getId();
		
		
		
		
		/* Orderitem orderitem=new Orderitem();
		 orderitem.setNumber(num);
		 orderitem.setPid(pid);
		 orderitem.setUid(user.getId());
		 orderitem.setOid(0);
		 
		 List<Orderitem> list_cart=new ArrayList<Orderitem>();
		 
		 if(user==null){
			 //cookie
			 if(list_cart_cookie==null || list_cart_cookie.equals("")){
				 list_cart.add(orderitem);
				
			 }else {
				list_cart=MyJsonUtil.json_to_list(list_cart_cookie,Orderitem.class);
				boolean b=if_new_cart(list_cart,orderitem);
				if(b){
					//new cart
					list_cart.add(orderitem);
				}else {
					//older cart
					for (int i = 0; i < list_cart.size(); i++) {
						if(list_cart.get(i).getPid()==orderitem.getPid()){
							list_cart.get(i).setNumber(list_cart.get(i).getNumber()+orderitem.getNumber());
							
						}
					}
				}
			}
			 Cookie cookie=new Cookie("list_cart_cookie",MyJsonUtil.list_to_json(list_cart));
				cookie.setMaxAge(60*60*24);
				 response.addCookie(cookie);
			 
		 }else{
			 //db
			 list_cart=(List<Orderitem>) session.getAttribute("list_cart_session");
			
			 boolean b=ms.getOrderitemcart(orderitem);
			 if(!b){
				 ms.addShopCart(orderitem);
				 if(list_cart==null || list_cart.isEmpty()){				
					 list_cart=new ArrayList<Orderitem>();
					 list_cart.add(orderitem);
					 session.setAttribute("list_cart_session", list_cart);
					 }else{
						 list_cart.add(orderitem);
						
					 }
				 
			 }else {
					for (int i = 0; i < list_cart.size(); i++) {
						if(list_cart.get(i).getPid().intValue()==orderitem.getPid().intValue()){
							list_cart.get(i).setNumber(list_cart.get(i).getNumber()+orderitem.getNumber());
							ms.updateShopCart(list_cart.get(i));
							System.out.println(list_cart.get(i));
						}
					}
					
					}
			}
			 
			 
		 return "redirect:forecart?uid="+user.getId();
			 
		 }
			// int oiid=0;
		
			
		 }*/
		
	}
		 
	
	private boolean if_new_cart(List<Orderitem> list_cart, Orderitem orderitem) {
	boolean b=true;
	for (int i = 0; i < list_cart.size(); i++) {
		
		if(list_cart.get(i).getPid()==orderitem.getPid()){
			b=false;
		}
	}
		return b;
	}


	@RequestMapping("forecart")
	public String forecart(Integer oiid,Integer uid,Model model){
		
        List<Orderitem> orderitems=ms.getCartOrderitem(uid);
		
		
		
		model.addAttribute("ois", orderitems);
		
		return "fore/cart";
		
		
	}
		
	@RequestMapping("forebought")
	public String getforebought(HttpSession session,Model model,Integer oid){
		 User user =(User)  session.getAttribute("user");
		List<Order> orders=ms.getBought(user.getId(),oid);
	
		float total=0;
		
		
		for (int i = 0; i < orders.size(); i++) {
			orders.get(i).setTotal(orders.get(i).getOrderitems().get(i).getNumber()*orders.get(i).getOrderitems().get(i).getProduct().getPromoteprice());
			total+=orders.get(i).getOrderitems().get(i).getNumber()*orders.get(i).getOrderitems().get(i).getProduct().getPromoteprice();
			
		}
		
		
	
		model.addAttribute("os", orders);
		
		return "fore/bought";
		
	}
	@RequestMapping("foreboughtop")
	public String getboughtop(Model model,Integer uid){
		
		List<Order> orders=ms.getBoughtop(uid);
	
		
	
		model.addAttribute("os", orders);
		
		return "fore/bought";
		
	}
	@RequestMapping("deleteOrderHide")
	public String deleteOrderHide(Integer oid){
		
		ms.updateOrderHide(oid);
		
		return "redirect:forebought";
	}
	
	@RequestMapping("waits")
	public String waits(String status,HttpSession session,Model model){
		
		 User user =(User)  session.getAttribute("user");
			List<Order> orders=ms.getWaitsBought(user.getId(),status);
			model.addAttribute("os", orders);
		System.out.println(status);
		return "fore/bought";
	}
	
	@RequestMapping("foreconfirmPay")
	public String getOrderConfirmPay(Integer oid,Model model){
		
		List<Order> orders=ms.getOrderConfirmPay(oid);
		model.addAttribute("o",orders);
		return "fore/confirmPay";
		
		
	}
	@RequestMapping("foreorderConfirmed")
	public String getforeorderConfirmed(Integer oid){
		
		ms.updateOrderConfirm(oid);
		
		return "fore/orderConfirmed";
		
	}
	
	@RequestMapping("forereview")
	public String getReview(Integer oid,Model model){
		
		 
		List<Order> order=ms.getOrferReview(oid);
		model.addAttribute("o",order);
		return "fore/review";
		
		
	}
	
	
	
	
		
	
	
	
	
	
	

}

