package com.lvym.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;







import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvym.beans.Category;
import com.lvym.beans.Order;
import com.lvym.beans.Product;
import com.lvym.beans.Productimage;
import com.lvym.beans.Property;
import com.lvym.beans.Propertyvalue;
import com.lvym.service.AdminService;
@Controller
public class AdminController {

	@Autowired
	AdminService ads;
	
	/**
	 * ---------------------------分类模块
	 * @param pn
	 * @param map
	 * @return
	 */
	@RequestMapping("admin_category_list")
	public String getAdmin_category_list(@RequestParam(value="pn" ,defaultValue="1")Integer pn,Map<String,Object> map){
          
		PageHelper.startPage(pn,3);
		
		List<Category> categories=ads.getAdmin_category_list();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo page = new PageInfo(categories,5);
		
		map.put("pageInfo", page);
		return "admin/listCategory";
	}
	/**
	 *          分类编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_category_edit")
	public String getAdmin_category_edit(Integer id,Model model){
		Category categories=ads.getAdmin_category_edit(id);
		model.addAttribute("c", categories);
		
		return "admin/editCategory";
	}
	/**
	 *        删除
	 * @param id
	 * @return
	 */
    @RequestMapping("admin_category_delete")
    public String deleteAdmin_category_delete(Integer id){
    	int count=ads.deleteAdmin_category_delete(id);
    	
    	return "redirect:admin_category_list";
    }
   /**
    *    更新
    * @param file
    * @param request
    * @param id
    * @param name
    * @return
    * @throws IllegalStateException
    * @throws IOException
    */
       @SuppressWarnings("unused")
      @RequestMapping(value="admin_category_update",method=RequestMethod.PUT)
    public String updateAdmin_category_update(@RequestParam("image")MultipartFile file,HttpServletRequest request,Integer id,String name) throws IllegalStateException, IOException{
    
	   System.out.println(file.getOriginalFilename().toString());
    	
    	
    	if(file!=null){
    		// 获得上传路径
    					// 获得项目运行时的路径+files 一定要保证根目录中有files这个文件夹
    				String path=request.getServletContext().getRealPath("/img/category");
    				//	String path="D:/Java/testUpload";
    				System.out.println(path);
    				//原名
    					String filename=file.getOriginalFilename();
    					String jpg =filename.substring(filename.lastIndexOf("."));
		
    					String imgNo =filename.substring(0,filename.indexOf("."));
		
		System.out.println(imgNo);
		int No=0;
		for (int i=0;i<=imgNo.length();i++) {
			No=(int) Math.round(Math.random()*i*12);
		}
		
			// 进行上传File 设置保存的路径和文件名
			
		file.transferTo(new File(path+"/"+No+jpg)); 
		Category categories=ads.getAdmin_category_edit(No);
		if(categories==null){
			
			ads.updateCategoryImages(No,id,name);
			
		}else{
			int newNo=(int) Math.round(Math.random()*9);
			ads.updateCategoryImage(newNo,id,name);
		}
		    				
    			return "admin/listCategory";	
    		
    	}else{
    		return "admin/editCategory";
    		
    	}
    
    }
    /**
     *      分类添加
     * @param file
     * @param request
     * @param name
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value="admin_category_add",method=RequestMethod.POST)
   public String addAdmin_category_add(@RequestParam("image")MultipartFile file,HttpServletRequest request,String name,Integer pn) throws IllegalStateException, IOException{
	   
	   if(file!=null){
		   //获取上传路径
		   
		   String path=request.getServletContext().getRealPath("/img/category");
		   
 		   String fileName=file.getOriginalFilename();
		   String imagesSuffix=fileName.substring(fileName.lastIndexOf("."));
		 
		   
		   Category categories=ads.getCategory();
						int No=categories.getId()+1;
				// 进行上传File 设置保存的路径和文件名
				
			file.transferTo(new File(path+"/"+No+imagesSuffix)); 
		
			ads.addAdmin_category_add(name);
			
				
			return "redirect:admin_category_list?pn="+pn;
	    		
	    	}else{
	    		return "admin/editCategory";
	    		
	    	}
   }
    
    /**
     * ---------------------------属性模块
     * @param pn
     * @param cid
     * @param map
     * @return
     */
	@RequestMapping("admin_property_list")
	public String getAdmin_property_list(@RequestParam(value="pn" ,defaultValue="1")Integer pn,Integer cid,ModelMap map){
		PageHelper.startPage(pn,3);
		List<Property> properties=ads.getAdmin_property_list(cid);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo page = new PageInfo(properties,5);
		map.addAttribute("pageInfo", page);
		
		Category categories=ads.getAdmin_category_edit(cid);
		map.addAttribute("c", categories);
		return "admin/listProperty";
	}
	/**
	 *                编辑
	 * @param id
	 * @param model
	 * @return
	 */
	 @RequestMapping("admin_property_edit")
	    public String getAdmin_property_edit(Integer id,Model model){
	    	
	    	Property property=ads.getAdmin_property_edit(id);
	    	model.addAttribute("p", property);
	    	
	    	
	    	return "admin/editProperty";
	    }
	 
	 /**
	  *      更新属性
	  */
	 @RequestMapping(value="admin_property_update")
	 public String updateAdmin_property_update(Integer id,String name,Integer pn,Integer cid){
		 
		 ads.updateAdmin_property_update(id,name);
		 
		 return "redirect:admin_property_list?pn="+pn+"cid="+cid;
	 }
	    /**
	     *              删除
	     * @param id
	     * @return
	     */
	    @RequestMapping("admin_property_delete")
	     public String deleteAdmin_property_delete(Integer id,Integer pn){
	    	
	    	int count=ads.deleteAdmin_property_delete(id);
	    	
	    	return "redirect:admin_property_list?pn="+pn+"cid="+id;
	    }
	    
	    /**
	     *               添加
	     * @param name
	     * @param cid
	     * @return
	     */
	    @RequestMapping(value="admin_property_add",method=RequestMethod.POST)
	    public String addAdmin_property_add(String name,Integer cid,Integer pn){
	    	ads.addAdmin_property_add(name,cid);
	    	
	    	return "redirect:admin_property_list?pn="+pn+"cid="+cid;
	    }
	
	
	/**
	 *  --------------------产品模块
	 * @param pn
	 * @param cid
	 * @param map
	 * @return
	 */
	@RequestMapping("admin_product_list")
	public String getAdmin_product_list(@RequestParam(value="pn" ,defaultValue="1")Integer pn,Integer cid,ModelMap map){
		PageHelper.startPage(pn,3);
		List<Product> products=ads.getAdmin_product_list(cid);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo page = new PageInfo(products,5);
		map.addAttribute("pageInfo", page);
	
		Category categories=ads.getAdmin_category_edit(cid);
		map.addAttribute("c", categories);
		
		return "admin/listProduct";
	}
	/**
	 *     图片管理
	 * @return
	 */
    @RequestMapping("admin_productImage_list")
    public String getAdmin_productImage_list(Integer pid,Model model){
    	List<Productimage> productimage=ads.getAdmin_productImage_lists(pid);
    	model.addAttribute("pisSingle", productimage);
    	List<Productimage> productimages=ads.getAdmin_productImage_list(pid);
    	model.addAttribute("pisDetail", productimages);
    	Product product=ads.getProductID(pid);
    	model.addAttribute("product", product);
    	return "admin/listProductImage";
    }
   /**
    *     设置属性值
    * @return
    */
    @RequestMapping("admin_propertyValue_edit")
    public String getAdmin_propertyValue_edit(Integer pid,Model model){
    	
    	List<Propertyvalue> propertyvalues=ads.getAdmin_propertyValue_edit(pid);
    	model.addAttribute("pvs", propertyvalues);
    	return "admin/editPropertyValue";
    }
   /**
    *     编辑
    * @return
    */
    @RequestMapping("admin_product_edit")
    public String getAdmin_product_edit(Integer id,Model model){
    	
    	Product product=ads.getAdmin_product_edit(id);
    	model.addAttribute("p", product);
    	return "admin/editProduct";
    }
    
    /**
     *       删除
     * @return
     */
    @RequestMapping("admin_product_delete")
    public String deletetAdmin_product_delete(Integer id,Integer pn,Integer cid){
    	
    	int count=ads.deleteAdmin_product_delete(id);
    	
    	return "redirect:admin_product_list?pn="+pn+"cid="+cid;
    }
    
    /**
     *                  新增产品
     * @param file
     * @param request
     * @param type
     * @param pid
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */               
    @RequestMapping(value="admin_productImage_add",method=RequestMethod.POST)
    public String addAdmin_productImage_add(@RequestParam("image")MultipartFile file,HttpServletRequest request,String type,Integer pid) throws IllegalStateException, IOException{
    	 if(file!=null){
  		   //获取上传路径
    		 
    		 String path;
  		 if(type.equalsIgnoreCase("type_single")){
  			path=request.getServletContext().getRealPath("/img/productSingle");
  			 
  		 }else{
  			 
  			path=request.getServletContext().getRealPath("/img/productDetail");
  		 }
  		   
  		   
  		   
   		   String fileName=file.getOriginalFilename();
  		   String imagesSuffix=fileName.substring(fileName.lastIndexOf("."));
  		 
  		   Productimage productimage=ads.getProductimageID();
  		  
  		   int No=productimage.getId()+1;
  				// 进行上传File 设置保存的路径和文件名
  				
  			file.transferTo(new File(path+"/"+No+imagesSuffix)); 
  		
  			ads.addAdmin_productImage_add(type,pid);
  		
  				
  			return "redirect:admin_productImage_list?pid="+pid;
  			
  	    	}else{
  	    		return "admin/admin_productImage_list";
  	    		
  	    	}
    	
    }
    /**
     *      delete
     */
    
    @RequestMapping("admin_productImage_delete")
    public String deleteAdmin_productImage_delete(Integer id){
    	
    	int count=ads.deleteAdmin_productImage_delete(id);
    	
    	 return "redirect:admin_productImage_list?pid="+id;
    }
    
    @RequestMapping(value="admin_product_add",method=RequestMethod.POST)
    public String addAdmin_product_add(Product product,Integer pn,Integer cid){
    	ads.addAdmin_product_add(product);
    	return "redirect:admin_product_list?pn="+pn+"cid="+cid;
    }
    @RequestMapping(value="admin_product_update",method=RequestMethod.PUT)
    public String updateAdmin_product_update(Product product,Integer pn,Integer cid){
    	ads.updateAdmin_product_update(product);
    	return "redirect:admin_product_list?pn="+pn+"cid="+cid;
    }
    
    
    
/**
 *  --------------订单管理
 */
    @RequestMapping("admin_order_list")
    public String getAdmin_order_list(@RequestParam(value="pn" ,defaultValue="1")Integer pn,Model model){
    	PageHelper.startPage(pn,3);
    	List<Order> orders=ads.getAdmin_order_list();
    	
		PageInfo page = new PageInfo(orders,5);
    	for (int i = 0; i <=orders.size(); i++) {
    		System.out.println("------"+i);
    		System.out.println("dddddd"+orders.size());
    		
    	}
    	
    	
		
		model.addAttribute("pageInfo", page);
    	
    	
    	return "admin/listOrder";
    	
    	
    	
    }
    
    @RequestMapping("admin_order_delivery")
    public String updateAdmin_order_delivery(@RequestParam(value="pn" ,defaultValue="1")Integer pn,Integer id){
    	
    	ads.updateAdmin_order(id);
    	
    	return "redirect:admin_order_list?pn="+pn;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
