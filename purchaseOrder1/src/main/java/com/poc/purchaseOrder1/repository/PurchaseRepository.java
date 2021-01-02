package com.poc.purchaseOrder1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.purchaseOrder1.entities.PurchaseEntity;
import com.poc.purchaseOrder1.entities.UserEntity;


public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
	public List<PurchaseEntity> findByUser(UserEntity u);
	//scheduled and async
    public List<PurchaseEntity> findByStatus(String status);
}
