//package com.poc.purchaseOrder1.ws.Endpoint;
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.xml.datatype.XMLGregorianCalendar;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//import com.poc.purchaseOrder1.services.IPurchaseService;
//import com.poc.purchaseOrder1.services.IUserService;
//import com.example.types.helloworld.PurchaseListType;
//import com.example.types.helloworld.PurchaseType;
//
//
//@Endpoint("/helloworld")
//public class HelloWorldEndpoint {
//	
//	// purchaseService
//	@Autowired
//	IPurchaseService pService;
//	
//	@Autowired
//	IUserService uService;
//
//	// logger
//	private static final Logger log = LoggerFactory.getLogger(HelloWorldEndpoint.class);
//
//	//later change this to: namespace="http://poc.com/purchaseOrder1/ws/models/"
//	
//	@PayloadRoot(namespace="http://example.com/types/helloworld", 
//	localPart="purchase")
//	@ResponsePayload
//	public PurchaseListType getPurchases(@RequestPayload PurchaseType request) {
//	
//		log.info("Soap Service Invoked......." );
//		
//		// basically if the status == Accepted, then, 
//		// you consider this as a request for list of 
//		// purchases from the database. with status "Accepted"
//		// And in response you send back. the List of Purchases.
//		
//		String status = request.getStatus();
//		
//		// purchases from db.
//		List<com.poc.purchaseOrder1.models.Purchase> list = new ArrayList<>();
//	
//		PurchaseListType responseList = new PurchaseListType();
//		
//		if(status.equals("Accepted")) {
//		
//			Integer _id = (int)request.getUserid();
//			list.addAll(this.pService.getAllPurchases(uService.getUserById(_id)));
//			
//			responseList
//			.getList()
//			.addAll(list.stream().map(p -> convertToResponse(p)).collect(Collectors.toList()));
//		}
//		log.info("Soap Service response has N objects, N = " + list.stream().map(x -> x.getPurchaseid()).count() );
//		
//		return responseList;
//		
//	}
//	
//	
//	private static PurchaseType convertToResponse(com.poc.purchaseOrder1.models.Purchase purchase) {
//
//		PurchaseType response = new PurchaseType();
//
//		response.setPurchaseid(purchase.getPurchaseid());
//		response.setUserid(purchase.getUserid());
//		response.setSitename(purchase.getSitename());
//		response.setProjectname(purchase.getProjectname());
//		response.setProjectType(purchase.getProjectType());
//		response.setSiteProjectid(purchase.getSiteProjectid());
//		response.setShiptoLocationCode(purchase.getShiptoLocationCode());
//		
//		// TODO: need to convert Date to XMLGregorianCalender Type.
//		GregorianCalendar cal = new GregorianCalendar();
//		cal.setTime(purchase.getDuedate());
//		
//		// response.setDuedate(cal.getTime());
//		
//		response.setQuantity(purchase.getQuantity());
//		response.setTotalcost(purchase.getTotalcost());
//		response.setStatus(purchase.getStatus());
//		response.setProductname(purchase.getProductname());
//		
//		return response;
//	}
//
//}