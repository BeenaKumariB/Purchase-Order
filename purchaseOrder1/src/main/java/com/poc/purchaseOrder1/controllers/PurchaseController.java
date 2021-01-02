package com.poc.purchaseOrder1.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.purchaseOrder1.models.Purchase;
import com.poc.purchaseOrder1.models.User;
import com.poc.purchaseOrder1.servicesImpl.PurchaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.poc.purchaseOrder1.ws.HelloWorldClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("PO-service/purchase-service")
@CrossOrigin("*")
public class PurchaseController {

	
	@Autowired
	PurchaseService pService;
	
	private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);
	
	@Autowired
	HelloWorldClient soapClient;
	
	public PurchaseController() {
		// TODO Auto-generated constructor stub
	}
	

	//@GetMapping("/{id}")
	@RequestMapping(value="/getPurchaseById/{id}",method=RequestMethod.GET)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Purchase>> getPurchasesById(@PathVariable Integer id) {
		
		User u = new User();
		u.setUserid(id);
		
		System.out.println(id);
		List<Purchase> list = pService.getAllPurchases(u);
		
		return new ResponseEntity<List<Purchase>>(list, HttpStatus.ACCEPTED);
	
	}
	
	
	//@PostMapping
	@RequestMapping(value="/savePurchase",method=RequestMethod.POST)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity savePurchase(@RequestBody Purchase p) throws Exception {
		
		int status = pService.savePurchase(p) != null ? 1: 0;
		if(status == 0) return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
    //@DeleteMapping ("/{id}")
    @RequestMapping(value="/deleteById/{id}",method=RequestMethod.DELETE)
    @ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
       
        int status = pService.deletePurchase(id);
        if(status == 0) return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    // CALLING THE SOAP-SERVICE. HERE. At every 12'th hour of the day.
    @Scheduled(cron="* * 12 * * *")
    @RequestMapping(value="/purchase",method=RequestMethod.GET)
    public ResponseEntity<List<Purchase>> poPoll() throws Exception {
    	
    	log.info("scheduled method: poPoll() is hit............");
    	
		List<Purchase> asyncPurchaseList = pService.fetchList().get(3, TimeUnit.SECONDS);
		
		log.info("purchase list from scheduler : {} ", asyncPurchaseList);
		
		return new ResponseEntity<List<Purchase>>(asyncPurchaseList, HttpStatus.ACCEPTED);
	
	}
}
