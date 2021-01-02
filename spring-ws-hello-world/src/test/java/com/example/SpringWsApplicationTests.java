package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.ws.client.HelloWorldClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringWsApplicationTests {

  @Autowired
  private HelloWorldClient helloWorldClient;

  @Test
  public void testSayHello() {
    assertNotNull(helloWorldClient.getPurchase(1L,1L,"sitename","projectname","projectType",
    		"siteProjectid",0, new Date(),1L,100L,"status","productname"));
  }
}
