package com.example.ws.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.example.types.helloworld.ObjectFactory;
import com.example.types.helloworld.PurchaseListType;
import com.example.types.helloworld.PurchaseType;

@Endpoint
public class HelloWorldEndpoint {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(HelloWorldEndpoint.class);

  @PayloadRoot(
      namespace = "http://example.com/types/helloworld",
      localPart = "purchase")
  @ResponsePayload
  public PurchaseType getPurchase(@RequestPayload PurchaseType request) {
	// request.setRequestProperty("Content-Type", "application/soap-xml; charset=utf-8");
    LOGGER.info("Endpoint received purchase[purchaseid={},userid={},sitename={},projectname={},projectType={},siteProjectid={},shiptoLocationCode={},duedate={},quantity={},totalcost={},status={},productname={}]",
        request.getPurchaseid(),
        request.getUserid(),
        request.getSitename(),
        request.getProjectname(),
        request.getProjectType(),
        request.getSiteProjectid(),
        request.getShiptoLocationCode(),
        request.getDuedate(),
        request.getQuantity(),
        request.getTotalcost(),
        request.getStatus(),
        request.getProductname());
    
    LOGGER.info("XML Request = {}", request);

    ObjectFactory factory = new ObjectFactory();
    PurchaseListType response = factory.createPurchaseListType();
    
    PurchaseType purchase1 = new PurchaseType();
    purchase1.setPurchaseid(1);
    purchase1.setStatus("Processed");
    purchase1.setProductname("Mac Book Pro");
    
    response.getList().add(purchase1);
    LOGGER.info("Transaction details: --------   ",response.getList());
    
    
    return purchase1;
  }
}