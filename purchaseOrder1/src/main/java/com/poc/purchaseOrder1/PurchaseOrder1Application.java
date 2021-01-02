package com.poc.purchaseOrder1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableDiscoveryClient
@SpringBootApplication
//@EnableSwagger2
public class PurchaseOrder1Application {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseOrder1Application.class, args);
	}

//	@Bean
//	@Primary
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}
	
	
}
