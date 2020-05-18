package com.rid.oc.ocGradleDemo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@GetMapping("/")
	public String status() {
		return "Application is Up!!";
	}
	
	@GetMapping("/Hello")
	public String hi() {
		return "Hi Riddhiman!!";
	}
}
