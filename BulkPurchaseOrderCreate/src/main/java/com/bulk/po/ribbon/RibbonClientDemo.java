package com.bulk.po.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bulk.po.feign.FeignClientDemo;
import com.bulk.po.model.ExcelModel;

@Component("demoRibbon")
public class RibbonClientDemo implements FeignClientDemo{


	@Autowired
	@LoadBalanced
	private RestTemplate loadBalanced;
	
	@Override
	public String render(ExcelModel excel) {

		final String encodeUrl= "http://purchase-order-rest/PO-service/purchase-service/savePurchase";
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		
		final HttpEntity<?> taskReq = new HttpEntity<>(excel, headers);
		
		final ResponseEntity<String> responseEntity = this.loadBalanced.exchange(encodeUrl, HttpMethod.POST, taskReq, String.class);
		
		return responseEntity.getBody();
	}

}
