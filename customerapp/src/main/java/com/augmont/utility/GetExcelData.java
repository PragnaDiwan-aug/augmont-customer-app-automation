package com.augmont.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelData {
	
		XSSFWorkbook workBook;
		
		public  GetExcelData() {
			
			File src=new File("./testdata/AugMontData.xlsx");
			try {
				FileInputStream fis=new FileInputStream(src);
				workBook=new XSSFWorkbook(fis);
			} catch (Exception e) {
				System.out.println("unable to locate TestData excel file"+e.getMessage());
			}
		}
		
		public String getData(String sheetName,int row,int column) {
			return workBook.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		public double getNumaricData(String sheetName,int row,int column) {
			return workBook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		}
		public long getNumaricDataLong(String sheetName,int row,int column) {
			return (long) workBook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		}
		public int getNumaricDataInt(String sheetName,int row,int column) {
			return (int) workBook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		}
	}
