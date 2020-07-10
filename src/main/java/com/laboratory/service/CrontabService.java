package com.laboratory.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratory.component.CrontabParser;
import com.laboratory.component.GlobalException;

@Service
public class CrontabService {

	@Autowired
	CrontabParser parse;

	public HashMap<String,String> toKor(String param) throws Exception{
		String[] list;
		if(param.contains("\n")) {
			list = param.split("\n");
		}
		else {
		   list = new String[1];
		   list[0] = param;
		}

		if(list == null) {
			new GlobalException().badParamException();
		}
		return parse.getKor(list);
	}
}
