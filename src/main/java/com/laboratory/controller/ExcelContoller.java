package com.laboratory.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laboratory.service.ExcelService;

@Controller
public class ExcelContoller {
	
	@Autowired
	ExcelService service;
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String,Object> upload(@RequestParam MultipartFile file){
		Map<String,Object > rst = new HashMap<String,Object>();
		rst.put("RESULT",false);
		try {
			rst = service.parse(file);
			rst.put("RESULT",true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
}
