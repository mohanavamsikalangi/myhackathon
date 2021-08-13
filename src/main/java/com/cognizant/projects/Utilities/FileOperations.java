package com.cognizant.projects.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileOperations {
	public static String userDir = System.getProperty("user.dir");
	public static FileInputStream fis = null;
	public static FileOutputStream fos = null;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	Properties prop;

	public void readMatressesIntoExcelSheet(String[] prodname)
			throws FileNotFoundException, InterruptedException, IOException {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Matresses");
		for (int i = 0; i < 20; i++) {
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(prodname[i]);
		}
		sheet.autoSizeColumn(0);
		fos = new FileOutputStream("MatressesSubMenuList.xlsx");
		workbook.write(fos);
	}

	public void readItemsIntoExcelSheet(String[] names, String[] prices)
			throws FileNotFoundException, InterruptedException, IOException {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("NamesAndPricesOfBookshelves");
		for (int i = 0; i < 3; i++) {
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(names[i]);
			row.createCell(1).setCellValue(prices[i]);
		}
		sheet.autoSizeColumn(0);
		fos = new FileOutputStream("BookShelvesList.xlsx");
		workbook.write(fos);
	}

}
