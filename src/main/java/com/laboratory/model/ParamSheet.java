package com.laboratory.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;

import lombok.Data;

@Data
public class ParamSheet implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String 		index;
	private String 		sheetName;
	private ArrayList<ParamRow> rows;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public ArrayList<ParamRow> getRows() {
		return rows;
	}
	public void setRows(ArrayList<ParamRow> rows) {
		this.rows = rows;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
	
}
