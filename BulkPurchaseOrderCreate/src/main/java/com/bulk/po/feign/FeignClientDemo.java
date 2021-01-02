package com.bulk.po.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bulk.po.model.ExcelModel;

@Service
@FeignClient("demo")
public interface FeignClientDemo {

	@RequestMapping(value="/test-service/render", method=RequestMethod.POST)
	public String render(@RequestBody ExcelModel excel);

}
