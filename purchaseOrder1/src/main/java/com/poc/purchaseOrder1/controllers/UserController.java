package com.poc.purchaseOrder1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.poc.purchaseOrder1.entities.UserEntity;
import com.poc.purchaseOrder1.models.User;
import com.poc.purchaseOrder1.servicesImpl.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value="PO-service/users-service")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;
	
	public UserController() {
		
	}

	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public String welcome() {
		return "Hello, World";
	}
	
	//@GetMapping
	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET,produces="application/json")
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<User>> getAllUsers() {
	
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	//@PostMapping
	@RequestMapping(value="/saveUsers",method=RequestMethod.POST)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<User> saveUser(@RequestBody User u) throws Exception {
		
		System.out.println(u + "......................................................");
		User modUser = userService.saveUser(u);
		if(modUser == null) {
			throw new Exception("Invalid...");
		}
		return new ResponseEntity<User>(new User(), HttpStatus.ACCEPTED);
	}
	
	
//	@DeleteMapping
	@RequestMapping(value="/deleteUsers",method=RequestMethod.DELETE)
	@ApiOperation(value = "Returns output details", notes = "Search Service", response = boolean.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval service", response = boolean.class),
	@ApiResponse(code = 400, message = "Invalid input provided"),
	@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<User> deleteUser(@RequestBody User u) {
		int status = userService.deleteUser(u);
		if(status == 0) {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity(null, HttpStatus.ACCEPTED);
	}

}
