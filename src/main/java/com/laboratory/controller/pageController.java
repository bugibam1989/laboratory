package com.laboratory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class pageController{

	@RequestMapping(value = "/ys", method = RequestMethod.GET)
	public String swagger(){
		System.out.println("ys");
		return "/swagger-ui.html";
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
}
