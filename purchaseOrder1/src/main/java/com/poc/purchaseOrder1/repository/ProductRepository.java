package com.poc.purchaseOrder1.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poc.purchaseOrder1.entities.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

//	@Query("SELECT p FROM ProductEntity as p WHERE p.name LIKE CONCAT('%', :s, '%')")
//	public List<ProductEntity> search(String s);
	
	 public List<ProductEntity> findByNameContaining(String name);
//	
	
}
