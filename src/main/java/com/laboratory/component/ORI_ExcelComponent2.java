package com.laboratory.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.laboratory.model.ParamCell;
import com.laboratory.model.ParamRow;
import com.laboratory.model.ParamSheet;
import com.sun.el.stream.Stream;

public class ORI_ExcelComponent2 {
//	
//	Map<Integer, XSSFCellStyle> styleMap = new HashMap<Integer, XSSFCellStyle>();
//
//	/**
//	 * 
//	 * @param cell 0-타입,1값,2주소
//	 * @return
//	 */
//	public String[] getCellValAndAddr(Cell cell) {
//		String type = cell.getCellType().name();
//		String val = "";
//		String addr = cell.getAddress().formatAsString();
//		switch (cell.getCellType()) {
//			case NUMERIC: 
//					val = String.valueOf(cell.getNumericCellValue());
//				break;
//			case STRING:
//					val = cell.getStringCellValue();
//				break;
//			case FORMULA:
//					val = String.valueOf(cell.getDateCellValue());
//				break;
//			case BOOLEAN:
//					val = String.valueOf(cell.getBooleanCellValue());
//				break;
//			case ERROR:
//				val = "[ERROR]!!"; //FormulaError 여기에 에러코드 정리
//				break;
//			case BLANK: case _NONE: default:
//					//val = String.valueOf("공백");
//				break;
//		}
//		return new String[] { type ,val,addr };
//	}
//	
//	public String parsingStyle(CellStyle style) {
//		Color color = style.getFillBackgroundColorColor();
//		Color fore = style.getFillForegroundColorColor();
//		HSSFColor hssColor;
//		XSSFColor xSSFColor;
//		HSSFColor foreHss;
//		XSSFColor foreXss;
//		if (fore instanceof HSSFColor) {
//			foreHss = (HSSFColor) color;
//            System.out.println(String.format("  %s: #%s;%n","getFillBackgroundColorColor", foreHss.getHexString()));
//        } else if (fore instanceof XSSFColor) {
//        	foreXss = (XSSFColor) color; 
//            if(foreXss.isAuto()) return "isAuto";
//			if(foreXss.isIndexed()) {
//				int index = (int) foreXss.getIndex(); 
//				return org.apache.poi.ss.usermodel.IndexedColors.fromInt(index).name();
//			}
//			if(foreXss.isThemed()) return "isThemed";
//			if(foreXss.isRGB()) {
//				byte[] rgb = foreXss.getRGB();
//				StringBuilder sb = new StringBuilder();
//		        sb.append(String.format("  %s: #%02x%02x%02x;%n","getFillBackgroundColorColor", rgb[0], rgb[1], rgb[2]));
//		        System.out.println(sb.toString());
//		        return sb.toString();
//			}
//        }
//		 if (color instanceof HSSFColor) {
//			 hssColor = (HSSFColor) color;
//             System.out.println(String.format("  %s: #%s;%n","getFillBackgroundColorColor", hssColor.getHexString()));
//         } else if (color instanceof XSSFColor) {
//        	 xSSFColor = (XSSFColor) color; 
//        	 
//             if(xSSFColor.isAuto()) return "isAuto";
//             
// 			if(xSSFColor.isIndexed()) {
// 				int index = (int) xSSFColor.getIndex();
// 				return org.apache.poi.ss.usermodel.IndexedColors.fromInt(index).name();
// 			}
// 			
// 			if(xSSFColor.isThemed()) return "isThemed";
// 			if(xSSFColor.isRGB()) {
// 				byte[] rgb = xSSFColor.getRGB();
// 				StringBuilder sb = new StringBuilder();
// 		        sb.append(String.format("  %s: #%02x%02x%02x;%n","getFillBackgroundColorColor", rgb[0], rgb[1], rgb[2]));
// 		        System.out.println(sb.toString());
// 		        return sb.toString();
// 			}
//         }
//		return "";
//	}
//	
//	public String parseCellAttr(Cell cell) {
//		HashMap<String,Object> map = new HashMap<String, Object>();
//		map.put("columnIndex", cell.getColumnIndex());
//		Method[] methods = cell.getClass().getMethods();
//		for(Method m : methods) {
//			if(m.getName().contains("get")) {
//				if(!m.getName().contains("value") || !m.getName().contains("style")) {
//					try {
//						Object val = m.invoke(cell);
//						if(val != null) map.put(m.getName(), val);
//					} catch (Exception e) {
//					}
//				}
//			}
//		} 
//		return map.toString();
//	}
//	
//	
//	public HashMap<String,Object> parse(MultipartFile file) throws Exception{
//		HashMap<String,Object> rst = new HashMap<String,Object>();
//		InputStream inputStream = file.getInputStream();
//		ZipSecureFile.setMinInflateRatio(0);
//		Workbook workbook =  WorkbookFactory.create(inputStream); 
//		Workbook workbook2 = WorkbookFactory.create(true);
//		//
//		Iterator<Sheet> iter_sheet = workbook.sheetIterator();
//		Sheet sheet;
//		Sheet tmpSheet;
//		ArrayList<ParamSheet> mySheetList = new ArrayList<ParamSheet>();
//		System.out.println("파싱 시작" + new Date());
//		while(iter_sheet.hasNext()) {
//			sheet = iter_sheet.next();
//			tmpSheet = workbook2.createSheet(sheet.getSheetName());
//			ParamSheet mySheet = new ParamSheet();
//			mySheet.setSheetName(sheet.getSheetName());
//			Iterator<Row> iter_row = sheet.rowIterator(); 
//			ArrayList<ParamRow> rows = new ArrayList<ParamRow>(); 
//			while(iter_row.hasNext()) {
//				Row row = iter_row.next();
//				
//				Row tmpRow = tmpSheet.createRow(row.getRowNum());	// 1. 새 파일 행 생성
//				tmpRow.setHeight(row.getHeight());
//				
//				ParamRow myRow = new ParamRow();
//				myRow.setRowHeight(String.valueOf(row.getHeight()));
//				myRow.setRowNum(String.valueOf(row.getRowNum()));
//				Iterator<Cell> iter_cell = row.cellIterator();
//				ArrayList<ParamCell> cells = new ArrayList<ParamCell>(); 
//				while(iter_cell.hasNext()) {
//					Cell cell = iter_cell.next(); 
//					Cell tmpCell = tmpRow.createCell(cell.getColumnIndex());		// 2. 1에서 생성한 행의 n번째 열
//					ParamCell myCell = new ParamCell();
//					String[] val = getCellValAndAddr(cell);
//					myCell.setType(val[0]);
//					myCell.setVal(val[1]);
//					myCell.setAddr(val[2]);
//					cells.add(myCell);
//					tmpCell.setCellValue(val[1]);
//					int hash = cell.getCellStyle().hashCode();
//					XSSFCellStyle tmpCellStyle = (XSSFCellStyle) tmpSheet.getWorkbook().createCellStyle();
//					if(styleMap.containsKey(hash)) {
//						tmpCellStyle = styleMap.get(hash);
//					}else {
//						tmpCellStyle.cloneStyleFrom(cell.getCellStyle());
//						styleMap.put(hash, tmpCellStyle);
//					}
//					int colIndex = cell.getColumnIndex();
//					int width = sheet.getColumnWidth(colIndex);
//					tmpSheet.setColumnWidth(colIndex, width);
//					tmpCell.setCellStyle(tmpCellStyle); 
//					//myCell.setAttr(parseCellAttr(cell));
//					//myCell.setStyle(parsingStyle(cell.getCellStyle()));
//				} // cells 종료
//				myRow.setCellList(cells);
//				rows.add(myRow);
//			} // row Depth
//			mySheet.setRows(rows);
//			mySheetList.add(mySheet);
//			parseMeger();
//	//MERGE REGION
//			 for(int i = 0; i < sheet.getNumMergedRegions(); i++) {
//			    CellRangeAddress merge = sheet.getMergedRegion(i);
//				CellRangeAddress mergedCell = new CellRangeAddress(
//						merge.getFirstRow(), //first row (0-based)
//						merge.getLastRow(), //last row  (0-based)
//						merge.getFirstColumn(), //first column (0-based)
//						merge.getLastColumn()  //last column  (0-based)
//				);
//				try {
//					tmpSheet.addMergedRegion(mergedCell);
//					System.out.println(String.format("START ROW : %d, END ROW : %s, START COL  : %s , END COL  : %s"
//							,  merge.getFirstRow(),merge.getLastRow(),merge.getFirstColumn(),merge.getLastColumn()));
//				}catch(Exception e){
//					e.getMessage();
//				}
//			}
//		}// sheet Depth
//		System.out.println("파싱 끝" + new Date());
//		workbook2.write(new FileOutputStream(new File("D:/tmp/excels",String.valueOf(new Date().getTime()) + ".xlsx")));
//		rst.put("DATA", mySheetList );
//        if(workbook != null) {
//        	workbook.close();
//            inputStream.close();
//            workbook2.close();
//        }
//        
//		return rst;
//	}
////	XSSFCellStyle mergeRowStyle2 = (XSSFCellStyle) workbook.createCellStyle();
////	mergeRowStyle2.setFillForegroundColor(new XSSFColor(new byte[] {(byte) 192,(byte) 192,(byte) 192}, null));
////	mergeRowStyle2.setFillPattern(FillPatternType.FINE_DOTS);
//
//	
//	public static void main(String[] args) {
//		XSSFColor co = new XSSFColor(java.awt.Color.LIGHT_GRAY);
//		
//		System.out.println(java.awt.Color.LIGHT_GRAY);
//	}
	 
}
