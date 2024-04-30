package utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;	
	
	
	public static  List<String> getData(String path,String sheet,int r,int c) throws IOException{
		
		List<String> getdata=new ArrayList<String>();
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		String s;
		
		
		
		 for(int i=0;i<r;i++) {
			 row=ws.getRow(i);
			 cell=row.getCell(c);
		
			try 
			{
				
				
				DataFormatter formatter = new DataFormatter();
	            s = formatter.formatCellValue(cell);
	            
			}
			catch (Exception e) 
			{
				s="";
			}
			getdata.add(s);
		 }
		
		 return getdata;
		
	}
	
	
	public static void setData(String path,String sheet,int r,int c,String[] data) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		row=ws.createRow(r);
		for(int i=0;i<c;i++) {
			
			cell=row.createCell(i);
			cell.setCellValue(data[i]);
		}
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
		
	}

}
