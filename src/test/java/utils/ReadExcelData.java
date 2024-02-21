package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcelData {
	
	@DataProvider(name="CredentialSupplier")
	public Object[][] dataSupplier() throws IOException {
		
		//getting the excel file
		File excelFile = new File(System.getProperty("user.dir")+ "\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);  
		XSSFSheet sheet = workbook.getSheet("login");  //getting the required sheet name
			
			//getting the rows and columns
			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rowCount][columnCount];
			
			for(int r=0; r<rowCount; r++) {
				XSSFRow row = sheet.getRow(r + 1);
				
				for(int c=0; c<columnCount; c++) {
					XSSFCell cell = row.getCell(c);
					CellType cellType = cell.getCellType();
					
					switch(cellType) {
					
					case STRING:
						data[r][c] = cell.getStringCellValue();
						break;
						
					case NUMERIC:
						data[r][c] = Integer.toString((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
				}
				
			}
			workbook.close();
			
			return data;
		}
		
	}


