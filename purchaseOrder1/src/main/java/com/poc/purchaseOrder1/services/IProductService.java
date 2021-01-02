package com.poc.purchaseOrder1.services;

import java.util.List;

import com.poc.purchaseOrder1.models.Product;

public interface IProductService {

	
	public List<Product> getAllProducts();
	
	public Product save(Product p);
	
	public int deleteProduct(Integer id);
	
	public List<Product> searchProducts(String s);
	
}
