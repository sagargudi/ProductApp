package productApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import productApp.model.Product;

@Component
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	/*create*/
	@Transactional
	public void createProduct(Product product) {
		this.hibernateTemplate.saveOrUpdate(product);
		
	}
	
	
	/*get all the products*/
	
	 public List<Product> getProduct() {
		 List<Product> product=this.hibernateTemplate.loadAll(Product.class);
		 return product;
	 }
	
	//delete single product 
	 @Transactional
	 public void deleteProduct(int pid) {
		Product p =this.hibernateTemplate.load(Product.class, pid);//fetch the id
		 this.hibernateTemplate.delete(p);
	 }
	
	 
	 /*get the single product*/
	 
	 
	 public Product singleproductfetch(int pid) {
		return this.hibernateTemplate.get(Product.class, pid);//fetch the id
			
		 }
	 
	 
	 
	 
	 
	 
	 
	 
}
