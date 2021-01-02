package com.poc.purchaseOrder1.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.purchaseOrder1.entities.ProductEntity;
import com.poc.purchaseOrder1.models.Product;
import com.poc.purchaseOrder1.repository.ProductRepository;

@Service
public class ProductService {

	
	@Autowired
	ProductRepository prodRepo;
	
	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Product> getAllProducts() {

		return prodRepo.findAll().stream().map(pe -> entityToModelMapping(pe)).collect(Collectors.toList());
	}
	
	public Product save(Product p) {
		ProductEntity pe =  prodRepo.save(modelToEntityMapping(p));
		if(pe == null) return null;
		return p;
	}
	
	public int deleteProduct(Integer id) {
		ProductEntity pe = prodRepo.findById(id).orElse(null);
		if(pe == null) return 0;
		else {
			prodRepo.delete(pe);
			return 1;
		}
	}
	
	public List<Product> searchProducts(String s) {
		return prodRepo.findByNameContaining(s).stream().map(pe -> entityToModelMapping(pe)).collect(Collectors.toList());
	}
	
	private Product entityToModelMapping(ProductEntity entity) {
		
		Product product = new Product();
		product.setProductid(entity.getProductid());
		product.setName(entity.getName());
		product.setDescription(entity.getDescription());
		product.setManufacturer(entity.getManufacturer());
		product.setPrice(entity.getPrice());
		return product;
		
	}
	
	
	private ProductEntity modelToEntityMapping(Product product) {
		
		ProductEntity pe = new ProductEntity();
		pe.setProductid(product.getProductid());
		pe.setName(product.getName());
		pe.setDescription(product.getDescription());
		pe.setManufacturer(product.getManufacturer());
		pe.setPrice(product.getPrice());
		return pe;
	}
	
	

}
