package com.laboratory.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.service.CrontabService;



@RestController
public class ResController {

	@Autowired
	CrontabService service;


	@RequestMapping(value = "/crontab/toKor", method = RequestMethod.POST)
	public HashMap<String,String> getKor(
			@RequestParam(value= "data") String param,
			HttpServletRequest request) throws Exception{
		HashMap<String,String> rst = new HashMap<String, String>();
		rst = service.toKor(param);
		return rst;
	}


	@RequestMapping(value = "/crontab/toSet", method = RequestMethod.POST)
	public HashMap<String,String> toSet(
			@RequestParam(value= "data") String param,
			HttpServletRequest request) throws Exception{
		HashMap<String,String> rst = new HashMap<String, String>();
		System.out.println(param);
		rst = service.toKor(param);
		return rst;
	}

}
