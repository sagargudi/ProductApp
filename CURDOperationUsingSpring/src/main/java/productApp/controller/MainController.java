package productApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productApp.dao.ProductDao;
import productApp.model.Product;

@Controller
public class MainController {
	
	@Autowired
	private ProductDao item;
	
	@RequestMapping("/")
	public String home(Model m) {
		
		List<Product> products= item.getProduct();
		m.addAttribute("products", products);
		return "index";
	}
	
	//show add form
	@RequestMapping("add") 
	public String addProduct(Model m) {
		m.addAttribute("title", "Add product");
		return "addForm";
	}
	
	//show handle form
	@RequestMapping(value="/Handle-Product", method=RequestMethod.POST)
	public RedirectView handleproduct(@ModelAttribute Product product, HttpServletRequest request) {
		
		System.out.println(product);
		this.item.createProduct(product);
		RedirectView r= new RedirectView();
		
		r.setUrl(request.getContextPath()+"/");
		return r;
		
	}
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteHandler(@PathVariable("productId") int productId ,HttpServletRequest request) {
		
		
		this.item.deleteProduct(productId);
		RedirectView r= new RedirectView();
		
		r.setUrl(request.getContextPath()+"/");
		return r;
	}
	
	
	
	@RequestMapping("/update/{productId}")
	public String updateHandler(@PathVariable("productId") int productId , Model m) {
		
		
		Product updateProduct=this.item.singleproductfetch(productId);
		m.addAttribute("updateProduct", updateProduct);
		RedirectView r= new RedirectView();
		return "updateForm";
	}	


}
