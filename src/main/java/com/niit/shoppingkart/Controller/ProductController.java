package com.niit.shoppingkart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingkart.DAO.ProductDAO;
import com.niit.shoppingkart.model.Product;

@Controller
public class ProductController

{
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value="/Product",method = RequestMethod.GET)
	public ModelAndView landPage(@ModelAttribute("product")Product product,BindingResult result,Model model)
	{
		
		ModelAndView mv= new ModelAndView("/Admin");
		mv.addObject("userclickedProduct", "true");
		mv.addObject("allData",productDAO.list());
		return mv;
		
		/*model.addAttribute("allProduct",productDAO.list());
		return "Product";
		*/
	}
	 
	/*@RequestMapping(value="/Product")
	public String getAllData(@ModelAttribute("product")Product product,BindingResult result,Model model)
	{
	
		model.addAttribute("allProduct",productDAO.list());
		return "Product";
	}*/
	
	@RequestMapping(value="/addProduct")
public String addItem(@ModelAttribute("product") Product p){
		
		this.productDAO.saveOrUpdate(p);
		return "redirect:/Product";
		
	}
	@RequestMapping(value="/productById/{id}")
	public String editItem(@PathVariable("id") int id,RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("product",this.productDAO.get(id));
		return "redirect:/Product";
		
}
	@RequestMapping(value="/deleteProductById/{id}")
	public String deleteItem(@PathVariable("id") int id,Product product)
	{
		productDAO.delete(product);
		return "redirect:/Product";
		
}	
}