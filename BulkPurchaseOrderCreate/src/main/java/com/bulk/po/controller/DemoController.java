package com.bulk.po.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bulk.po.feign.FeignClientDemo;
import com.bulk.po.model.ExcelModel;
import com.bulk.po.service.ExcelParserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("/test-service")
@Api(value = "PO Services")
public class DemoController {

	@Autowired
	ExcelParserService svcXlsParser;
	
	@Autowired
	@Qualifier("demoRibbon")
	FeignClientDemo ribbonDemo;

	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ApiOperation(value = "Returns output details", notes = "File upload Service", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval service", response = String.class),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public String uploadFile(@RequestParam("file") MultipartFile excelUpload) throws Exception {
		
		ExcelModel o= svcXlsParser.parseExcelFromByteStream(excelUpload.getInputStream(), ExcelModel.class);
		System.out.print(o.toString());
		ribbonDemo.render(o);
		
		String test= "";
		 return "accepted";
		
	}
	

	
	@RequestMapping(value="/render", method=RequestMethod.POST)
	@ApiOperation(value = "Returns output details", notes = "Demo Feign Service", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful retrieval service", response = String.class),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "Data does not exist"), })
	@ResponseStatus(value = HttpStatus.OK)
	public String render(@RequestBody ExcelModel excel) {
		
		System.out.println("--details------"+excel.toString());
		 return "accepted";
	}
}
