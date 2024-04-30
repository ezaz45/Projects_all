package trivago;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class excelUtils
{
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;   
	

	public static String excel(int r,int c) throws IOException
	{
		String filepath = System.getProperty("user.dir")+("\\excelfile\\destinationvalue.xlsx");
		fi = new FileInputStream(filepath);
		 wb = new XSSFWorkbook(fi);
		ws = wb.getSheetAt(0);
		String testData = String.valueOf(ws.getRow(r).getCell(c));
		wb.close();
		return testData;
		
	}
	
    	
    		
    	
	public static void Output(List<String> hotelnames, List<String> hotelprices) throws Throwable {
		// TODO Auto-generated method stub
		String path= System.getProperty("user.dir")+"\\excelfile\\Excelouput.xlsx";
    	FileOutputStream file = new FileOutputStream(path);
    	XSSFWorkbook workbook = new XSSFWorkbook();
    	XSSFSheet sheet = workbook.createSheet("Sheet1");
    	for(int i =0; i<hotelprices.size();i++)
    	{
    		XSSFRow row = sheet.createRow(i);
    		
    				row.createCell(0).setCellValue(hotelnames.get(i));
    				row.createCell(1).setCellValue(hotelprices.get(i));
    				
    	}	
		
		workbook.write(file);
    	workbook.close();
    	file.close();
	}
	
	
    	
    
}


