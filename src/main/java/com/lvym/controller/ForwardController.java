package com.lvym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lvym.beans.Category;
import com.lvym.service.UserService;


@Controller
public class ForwardController {
	
	@Autowired
	UserService us;
	@RequestMapping("forehome")
	public String gethome(Model model){
		
		List<Category> categories=us.getAllCategory();
		model.addAttribute("cs",categories);
				return "fore/home";
	}
   @RequestMapping("loginPage")
   public String getloginPage(){
		return "fore/login";
}
   
   
   @RequestMapping("registerPage")
   public String getregisterPage(){
		return "fore/register";
}
  
	
}
