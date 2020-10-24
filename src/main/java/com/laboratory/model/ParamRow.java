package com.laboratory.model;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;

import lombok.Data;

@Data
public class ParamRow {
	private String	rowNum;
	private String	rowHeight;
	
	private ArrayList<ParamCell> cellList;

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(String rowHeight) {
		this.rowHeight = rowHeight;
	}

	public ArrayList<ParamCell> getCellList() {
		return cellList;
	}

	public void setCellList(ArrayList<ParamCell> cellList) {
		this.cellList = cellList;
	}

 
	
}
