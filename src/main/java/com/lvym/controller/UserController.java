package com.lvym.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lvym.beans.Category;
import com.lvym.beans.User;
import com.lvym.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService us;
	@RequestMapping(value="foreregister",method=RequestMethod.POST)
	public String addUser(String name,String password){
		
		us.addUser(name,password);
		
		return "fore/registerSuccess";
	}
	@GetMapping("forelogin")
	public String getForelogin(User user,HttpSession session,Model model){
		
		
		user=us.getForelogin(user);
		
			
		if(user==null){
			
			return "fore/login";
		}
		if(user.getName().equalsIgnoreCase("lvym")){
			
	List<User> use=us.getAllUser();	
		model.addAttribute("users", use);
			return "admin/listUser";
			
		}
		session.setAttribute("user", user);
		
		List<Category> categories=us.getAllCategory();
		model.addAttribute("cs",categories);
			System.out.println("0000000000000");
		return "fore/home";
	}
	@GetMapping("forelogout")
	public String cleanSession(HttpSession session){
		
		session.invalidate();    // 获取session信息，使session信息失效，直接返回登录界面，并连接跳转。
		return "fore/login";
	}
	
}
