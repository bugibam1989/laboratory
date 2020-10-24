package com.laboratory.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.laboratory.component.ExcelComponent;

@Service
public class ExcelService {
	
	@Autowired
	ExcelComponent excel;
	
	public HashMap<String,Object> parse(MultipartFile file) {
		HashMap<String,Object> rst = new HashMap<String, Object>();
		try {
			rst = excel.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}
	
}
