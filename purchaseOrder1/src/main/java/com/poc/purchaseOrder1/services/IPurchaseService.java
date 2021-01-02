package com.poc.purchaseOrder1.services;

import java.util.List;
import java.util.concurrent.Future;

import com.poc.purchaseOrder1.models.Purchase;
import com.poc.purchaseOrder1.models.User;

public interface IPurchaseService {

	public List<Purchase> getAllPurchases(User u);
	public Purchase savePurchase(Purchase p) throws Exception;
	public int deletePurchase(Integer id);
	public Future<List<Purchase>> fetchList() throws Exception;

	
}
