package com.mayikt.controller;

import com.mayikt.event.DemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@Autowired
	private DemoClient demoClient;

	@GetMapping("/event")
	public String event(String msg) {
		demoClient.publish(msg);
		return "test";
	}
	
}
