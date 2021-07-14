package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ImportResource("classpath:spring-job.xml")
public class ExcelDemoApp {

	public static void main(String[] args) {
		SpringApplication.run(ExcelDemoApp.class, args);
	}
	
}
