package selenium.com.qa.ohrm.actions;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

import net.bytebuddy.asm.Advice.Return;

public class BaseUtil {

	FileInputStream fileInputStream;
	File file;
	Properties properties;
	public String envFileName = "env.properties";
	public String testdataFileName = "testdata.xlsx";
	public String envFilePath;
	public static String configProp="";
	
	public Workbook xssfWorkbook=null;
//	public XSSFSheet worksheet;
	public XSSFCell cell;
	public XSSFRow row;
	public String columnValue="";
	
	
	
	public String rootDirPath = System.getProperty("user.dir");
	
	public String getConfigValueFromEnvFile(String propKey) {
		
		try {
			
			envFilePath = System.getProperty("user.dir") + "\\src\\config\\"+ envFileName;
			
			System.out.println("envFilePath : "+envFilePath);
			file = new File(envFilePath);
			fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			configProp = properties.getProperty(propKey);
			System.out.print("config property: "+configProp);
			
		} catch (Exception exception) {
			
		}
		
		return configProp;
	}
	
	
	public String readDataFromExcel(String sheetName, String fieldName) {
		
		String dataFilePath = System.getProperty("user.dir") + "\\src\\config\\"+testdataFileName;
		String fileExtension = testdataFileName.substring(testdataFileName.indexOf("."));
		
		
		System.out.println("dataFilePath : "+dataFilePath);
		
		try {
			file = new File(dataFilePath);
			fileInputStream = new FileInputStream(file);
			
			if(fileExtension.contains(".xlsx")){
				xssfWorkbook = new XSSFWorkbook(fileInputStream);
				
			} else if(fileExtension.contains(".xls")){
				xssfWorkbook = new HSSFWorkbook(fileInputStream);
			}
			Sheet sheet = xssfWorkbook.getSheet(sheetName);
			int rowsCount = sheet.getPhysicalNumberOfRows();
			System.out.println("Rows count is: "+rowsCount);
			for(int j=0; j < rowsCount+1 ; j++) {
				Row row = sheet.getRow(j);
				Row activeRow = sheet.getRow(j+1);
				
				for(int i = 0; i<=row.getLastCellNum();i++){
					String columnName = row.getCell(i).getStringCellValue().trim();
					System.out.println("column name is: "+columnName);
										
					if(columnName.equalsIgnoreCase(fieldName.trim())) {
						columnValue = activeRow.getCell(i).getStringCellValue().trim();
						System.out.println("Column Value : "+columnValue);
					}
				}
			}
			
			
						
		} catch(Exception e){
			
		}
		return columnValue;
	}
	
	public int createRandomNumber() {
		System.out.println("Genarate random numbers with specific length of the number");
		int random = (int) Math.pow(10, 6);
		random = random + new Random().nextInt(9*random);
		System.out.println("Random Number: "+random);
		return random;
	}
   
}
