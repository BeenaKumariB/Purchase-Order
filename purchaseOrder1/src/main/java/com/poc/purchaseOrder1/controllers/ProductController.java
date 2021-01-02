package com.poc.purchaseOrder1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.purchaseOrder1.models.Product;
import com.poc.purchaseOrder1.servicesImpl.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("PO-service/products-service")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductService pService;
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	//@GetMapping
	@RequestMapping(value="/getAllProducts",method=RequestMethod.GET)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Product>> getAllProducts() {
		
		List<Product> prods = pService.getAllProducts();
		return new ResponseEntity(prods, HttpStatus.OK);
	}
	
	//@PostMapping
	@RequestMapping(value="/saveProducts",method=RequestMethod.POST)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Object> saveProduct(@RequestBody Product p) {
		pService.save(p);
		return new ResponseEntity(HttpStatus.ACCEPTED);
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
		
		int status = pService.deleteProduct(id);
		if(status == 0) return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(value="/searchProduct",method=RequestMethod.GET)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Product>> searchProduct(@RequestParam(value="search") String str) {
		
		List<Product> list = pService.searchProducts(str); 
		return new ResponseEntity(list, HttpStatus.ACCEPTED);
	}
	
	
}


//	@GetMapping
//	public ResponseEntity<List<Product>> searchProduct(@Param(value="search") String str) {
//		
//		List<Product> list = pService.searchProducts(str); 
//		return new ResponseEntity(list, HttpStatus.ACCEPTED);
//	}


