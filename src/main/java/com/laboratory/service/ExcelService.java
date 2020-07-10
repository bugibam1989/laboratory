package com.laboratory.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelService {
	
	
	
	public Map<String,Object> parse(MultipartFile file) throws IOException{
		Map<String,Object> rst = new HashMap<String,Object>();
		InputStream inputStream = file.getInputStream();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);//엑셀읽기
        int sheetCnt = workbook.getActiveSheetIndex();
        System.out.println("SHEET CNT :" + sheetCnt);
        List<HSSFName> names = workbook.getAllNames();
        int i=0;
        while(names.iterator().hasNext()) {
        	System.out.println(String.format("[ %d ], %s", i,names.iterator().next().toString()));
        	i++;
        }
        HSSFSheet sheet = workbook.getSheetAt(0);//시트가져오기 0은 첫번째 시트
        int rows = sheet.getPhysicalNumberOfRows();//시트에서 총 행수
        if(workbook != null) {
        	workbook.close();
        }
		return rst;
	}
}
