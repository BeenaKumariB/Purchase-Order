package com.bulk.po;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClients
public class BulkPurchaseOrderCreateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkPurchaseOrderCreateApplication.class, args);
	}

}
