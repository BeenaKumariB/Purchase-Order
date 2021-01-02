package com.poc.purchaseOrder1.servicesImpl;

import java.sql.Date;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.purchaseOrder1.entities.PurchaseEntity;
import com.poc.purchaseOrder1.entities.UserEntity;
import com.poc.purchaseOrder1.models.Purchase;
import com.poc.purchaseOrder1.models.User;
import com.poc.purchaseOrder1.repository.PurchaseRepository;
import com.poc.purchaseOrder1.repository.UserRepository;
import com.poc.purchaseOrder1.services.IPurchaseService;
import com.poc.purchaseOrder1.ws.HelloWorldClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PurchaseService implements IPurchaseService {

	@Autowired
	PurchaseRepository pRepo;
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
    HelloWorldClient soapClient;
	
	private static Logger log = LoggerFactory.getLogger(PurchaseService.class);
	
	public PurchaseService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Purchase> getAllPurchases(User u) {
		
		Optional<UserEntity> ue = uRepo.findById(u.getUserid());
		if(ue.isPresent())
			return pRepo.findByUser(ue.get()).stream().map(pe -> entityToModelMapping(pe))
					.collect(Collectors.toList());
		else 
			return null;
	}
	
	public Purchase savePurchase(Purchase p) throws Exception {
		
		PurchaseEntity pe = pRepo.save(modelToEntityMapping(p));
		if(pe == null) return null;
		return p;
	}

	 public int deletePurchase(Integer id) {
	        PurchaseEntity pe = pRepo.findById(id).orElse(null);
	        if(pe == null) return 0;
	        else {
	            pRepo.delete(pe);
	            return 1;
	        }
	    }
	
	private PurchaseEntity modelToEntityMapping(Purchase purchase) throws Exception {
		
		PurchaseEntity pe = new PurchaseEntity();
		pe.setPurchaseid(purchase.getPurchaseid());
		pe.setSitename(purchase.getSitename());
		pe.setProjectname(purchase.getProjectname());

		Optional<UserEntity> oUser = uRepo.findById(purchase.getUserid());
		if(oUser.isPresent())
			pe.setUser(oUser.get());
		else 
			throw new Exception("user not Found");

		pe.setSiteProjectid(purchase.getSiteProjectid());
		pe.setShiptoLocationCode(purchase.getShiptoLocationCode());
		pe.setProjectType(purchase.getProjectType());
		pe.setDuedate((Date) purchase.getDuedate());
		pe.setQuantity(purchase.getQuantity());
		
		pe.setTotalcost(purchase.getTotalcost());
		pe.setStatus(purchase.getStatus());
		pe.setProductname(purchase.getProductname());
		
		return pe;
	}
	
	private Purchase entityToModelMapping(PurchaseEntity entity) {
		Purchase purchase = new Purchase();
		
		purchase.setPurchaseid(entity.getPurchaseid());
		purchase.setSitename(entity.getSitename());
		purchase.setProjectname(entity.getProjectname());
		
		purchase.setUserid(entity.getUser().getUserid());
	
		purchase.setSiteProjectid(entity.getSiteProjectid());
		purchase.setShiptoLocationCode(entity.getShiptoLocationCode());
		purchase.setProjectType(entity.getProjectType());
		purchase.setDuedate((Date) entity.getDuedate());
		purchase.setQuantity(entity.getQuantity());
		purchase.setTotalcost(entity.getTotalcost());
		purchase.setStatus(entity.getStatus());
		purchase.setProductname(entity.getProductname());
		
		
		return purchase;
	}

public Future<List<Purchase>> fetchList() throws Exception{
		
		// We fetch all The Purchases if their status is set to "Accepted".
		String S = "Accepted";
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<List<Purchase>> asyncList = executor.submit(() -> {
			
			
			List<PurchaseEntity> polist=pRepo.findByStatus(S);
			List<Purchase> response = polist.stream().map(po -> entityToModelMapping(po)).collect(Collectors.toList());
			
			
			log.info("{}", response.size());
			// Calling the web Client;
			if(!response.isEmpty()) {
				for(Purchase pur : response) {
					System.out.println("testing");
					String status = soapClient.sayHello(pur.getPurchaseid(), 
							pur.getUserid(), pur.getSitename(), pur.getProjectname(), 
							pur.getProjectType(), pur.getSiteProjectid(), pur.getShiptoLocationCode(), 
							pur.getDuedate(), pur.getQuantity(), pur.getTotalcost(), pur.getStatus(), 
							pur.getProductname());
					
					System.out.println("testing1");
					if(status.equals("processed")) {
						log.info("The purchaseId = {} is processed: ", pur.getPurchaseid());
					}
					
					pur.setStatus("Processed");
					this.savePurchase(pur);
				}			
			}
			
			return response;
		});
		
		return asyncList;
	}
}
