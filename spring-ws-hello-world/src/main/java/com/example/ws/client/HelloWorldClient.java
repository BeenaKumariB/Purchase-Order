package com.example.ws.client;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.example.types.helloworld.ObjectFactory;
import com.example.types.helloworld.PurchaseType;

@Component
public class HelloWorldClient {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(HelloWorldClient.class);

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  public String getPurchase(long purchaseid, long userid, String sitename, String projectname, String projectType, String siteProjectid, long shiptoLocationCode, Date duedate, long quantity, long totalcost, String status, String productname) {
    
//
	  
	XMLGregorianCalendar xmlDate = null;
	GregorianCalendar gc = new GregorianCalendar();
	gc.setTime(duedate);
	try {
		xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	} catch (DatatypeConfigurationException e) {
		e.printStackTrace();
	}
	
	
// 
	
	// System.setProperty("javax.xml.soap.MessageFactory","com.sun.xml.internal.messaging.saaj.soap.ver1_2.SOAPMessageFactory1_2Impl");
	ObjectFactory factory = new ObjectFactory();
    PurchaseType purchase = factory.createPurchaseType();
    

    purchase.setPurchaseid(purchaseid);
    purchase.setUserid(userid);
    purchase.setSitename(sitename);
    purchase.setProjectname(projectname);
    purchase.setProjectType(projectType);
    purchase.setSiteProjectid(siteProjectid);
    purchase.setShiptoLocationCode(shiptoLocationCode);
    purchase.setDuedate(xmlDate);
    purchase.setQuantity(quantity);
    purchase.setTotalcost(totalcost);
    purchase.setStatus(status);
    purchase.setProductname(productname);;
    
    LOGGER.info("Client sending person[purchaseid={},userid={},sitename={},projectname={},projectType={},siteProjectid={},shiptoLocationCode={},duedate={},quantity={},totalcost={},status={}]",
    		purchase.getPurchaseid(),
    		purchase.getUserid(),
    		purchase.getSitename(),
    		purchase.getProjectname(),
    		purchase.getProjectType(),
    		purchase.getSiteProjectid(),
    		purchase.getShiptoLocationCode(),
    		purchase.getDuedate(),
    		purchase.getQuantity(),
    		purchase.getTotalcost(),
    		purchase.getStatus(),
    		purchase.getProductname());

    PurchaseType status1 =
        (PurchaseType) webServiceTemplate.marshalSendAndReceive(purchase);

    LOGGER.info("Client received status='{}'",
        status1.getStatus());
    return status1.getStatus();
  }
  
}
