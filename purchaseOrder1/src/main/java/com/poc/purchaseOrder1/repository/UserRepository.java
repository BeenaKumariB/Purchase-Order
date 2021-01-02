package com.poc.purchaseOrder1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.purchaseOrder1.entities.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
}
