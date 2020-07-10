package com.laboratory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class pageController{
	
	@RequestMapping(value = "/swagger", method = RequestMethod.GET)
	public String swagger(){
		return "/swagger-ui";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/myHome", method = RequestMethod.GET)
	public String myHome() {
		System.out.println("myhome");
		return "myHome";
	}
	
	@RequestMapping(value = "/06", method = RequestMethod.GET)
	public String excel() {
		System.out.println("/06");
		return "jscell";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		System.out.println("test");
		return "test";
	}
}
