package com.laboratory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.laboratory.service.ExcelService;

@Controller
public class ExcelContoller {
	public static final String PRFIX = "excell/";
	
	@Autowired
	ExcelService service;
	
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<?> upload(@RequestParam MultipartFile file) throws Exception{
		return  ResponseEntity.ok(service.parse(file));
	}
	
	
	@RequestMapping(value = "/addTab", method = RequestMethod.GET)
	public String addTab() {
		return PRFIX + "addTab";
	}
	
	@RequestMapping(value = "/context", method = RequestMethod.GET)
	public String context() {
		return PRFIX + "context";
	}
	
	@RequestMapping(value = "/culType", method = RequestMethod.GET)
	public String culType() {
		return PRFIX + "culType";
	}
	@RequestMapping(value = "/getStyle", method = RequestMethod.GET)
	public String getStyle() {
		return PRFIX + "getStyle";
	}
	@RequestMapping(value = "/lazy", method = RequestMethod.GET)
	public String lazy() {
		return PRFIX + "lazy";
	}
	@RequestMapping(value = "/mergeCell", method = RequestMethod.GET)
	public String mergeCell() {
		return PRFIX + "mergeCell";
	}
	@RequestMapping(value = "/options", method = RequestMethod.GET)
	public String options() {
		return PRFIX + "options";
	}
	
	
}
