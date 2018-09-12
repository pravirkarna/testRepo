package ui.pageobjects.pagehelpers;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;


public class XlsReader 
{
	public static String filename = System.getProperty("user.dir");
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row   =null;
	public XSSFCell cell = null;
	public static String sActionKeyword=null;

	/**
	 * 
	 * @param path
	 */
	public XlsReader(String path) 
	{
		
		this.path=path;
		try 
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}




	public XlsReader(String path ,String rights) 
	{
		
		this.path=path;
		try 
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}catch (FileNotFoundException e) {			
			if (rights.equalsIgnoreCase("w")) {
				if(createWorkbook(path)){
					try {
						fis = new FileInputStream(path);
						workbook = new XSSFWorkbook(fis);
						sheet = workbook.getSheetAt(0);
						fis.close();
					} catch (Exception e1) {			
						e.printStackTrace();
					} 
				}
				
			} else {
				e.printStackTrace();
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	


		//To retrieve SuiteToRun and CaseToRun flag of test suite and test case.
		public String retrieveToRunFlag(String wsName, String colName, String rowName){
			
			int sheetIndex=workbook.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return null;
			else{
				int rowNum = getRowCount(wsName);
				int colNum = getColumnCount(wsName);
				int colNumber=-1;
				int rowNumber=-1;			
				
				XSSFRow Suiterow = sheet.getRow(0);				
				
				for(int i=0; i<colNum; i++){
					if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
						colNumber=i;					
					}					
				}
				
				if(colNumber==-1){
					return "";				
				}
				
				
				for(int j=0; j<rowNum; j++){
					XSSFRow Suitecol = sheet.getRow(j);				
					if(Suitecol.getCell(0).getStringCellValue().equals(rowName.trim())){
						rowNumber=j;	
					}					
				}
				
				if(rowNumber==-1){
					return "";				
				}
				
				XSSFRow row = sheet.getRow(rowNumber);
				XSSFCell cell = row.getCell(colNumber);
				if(cell==null){
					return "";
				}
				String value = cellToString(cell);
				return value;			
			}			
		}
		
		//To retrieve DataToRun flag of test data.
		public String[] retrieveToRunFlagTestData(String wsName, String colName){
			
			int sheetIndex=workbook.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return null;
			else{
				int rowNum = getRowCount(wsName);
				int colNum = getColumnCount(wsName);
				int colNumber=-1;
						
				
				XSSFRow Suiterow = sheet.getRow(0);				
				String data[] = new String[rowNum-1];
				for(int i=0; i<colNum; i++){
					if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
						colNumber=i;					
					}					
				}
				
				if(colNumber==-1){
					return null;				
				}
				
				for(int j=0; j<rowNum-1; j++){
					XSSFRow Row = sheet.getRow(j+1);
					if(Row==null){
						data[j] = "";
					}
					else{
						XSSFCell cell = Row.getCell(colNumber);
						if(cell==null){
							data[j] = "";
						}
						else{
							String value = cellToString(cell);
							data[j] = value;	
						}	
					}
				}
				
				return data;			
			}			
		}
		
		
		public ArrayList<ArrayList<Object>> retrieveTestDataAsArrayList(String wsName){
			
			int sheetIndex=workbook.getSheetIndex(wsName);
			if(sheetIndex==-1){
				return null;
			}
			else{
				int rowNum=getRowCount(wsName);
				int colNum= getColumnCount(wsName);
				 
				ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
				
				
				for(int i=1;i<rowNum;i++){
					XSSFRow row= sheet.getRow(i);
					ArrayList<Object> rowdata = new ArrayList<Object>(); 
					for(int j=0;j<colNum;j++){
						
						if(row==null){
							data.add(null);
							break;
						}
						else{
							XSSFCell cell = row.getCell(j);
							if(cell==null){
								rowdata.add("");
							}
							else{
								//cell.setCellType(Cell.CELL_TYPE_STRING);
								String value= cellToString(cell);
								rowdata.add(value);
							}
							
						}
					}
					data.add(rowdata);
				}
				return data;
			}
			
		}
		
		
		//To retrieve test data from test case data sheets.
		public Object[][] retrieveTestData(String wsName){
			int sheetIndex=workbook.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return null;
			else{
					int rowNum = getRowCount(wsName);
					int colNum= getColumnCount(wsName);
			
					Object data[][] = new Object[rowNum-1][colNum-2];
			
					for (int i=0; i<rowNum-1; i++){
						XSSFRow row =sheet.getRow(i+1);
						for(int j=0; j< colNum-2; j++){					
							if(row==null){
								data[i][j] = "";
								break;
							}
							else{
								XSSFCell cell = row.getCell(j);	
						
								if(cell==null){
									data[i][j] = "";							
								}
								else{
									//cell.setCellType(Cell.CELL_TYPE_STRING);
									String value = cellToString(cell);
									data[i][j] = value;						
								}
							}
						}				
					}			
					return data;		
			}
		
		}		
		
		
		public static String cellToString(XSSFCell cell){
			int type;
			Object result;
			DataFormatter formatter = new DataFormatter();
			switch (cell.getCellType()){
				case 0 :
					 if (DateUtil.isCellDateFormatted(cell)) {
	                     result= formatter.formatCellValue(cell);
	                 } else {
	                	 result = formatter.formatCellValue(cell);
	                 }
					
					break;
					
				case 1 : 
					result = cell.getStringCellValue();
					break;
					
				default :
					throw new RuntimeException("Unsupportd cell.");			
			}
			return result.toString();
		}
		
		


	// returns the row count in a sheet
	/**
	 * To get the Row count and returns the same integer.
	 * @param sheetName
	 * @return rowcount no.
	 */
	public int getRowCount(String sheetName)
	{
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else
		{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	// returns number of columns in a sheet	
		/**
		 * Returns the No of Coulums in a sheet.
		 * @param sheetName
		 * @return NoOfrows count.
		 */
		public int getColumnCount(String sheetName)
		{
			// check if sheet exists
			if(!isSheetExist(sheetName))
			 return -1;
			
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			if(row==null)
				return -1;
			
			return row.getLastCellNum();
		}
		
		
	
	// returns the data from a cell
	/**
	 * To read a data from a cell based on column name and returns the same value.
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @return cellText - data value from a excel cell.
	 * @exception Exception
	 */
	public String getCellData(String sheetName,String colName,int rowNum)
	{
		try
		{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		//System.out.println(cell.getCellType());
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA )
		{
			
			String cellText = new BigDecimal(cell.getNumericCellValue()).toString();
			//int i = (int)cell.getNumericCellValue();
			 // String cellText  = String.valueOf(i);
			 
			//String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell))
			  {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		          cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		          cellText = cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
		           //System.out.println(cellText);
		         }
	  
		return cellText;
		  }
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	
	
	
	// returns true if data is set successfully else false
	/**
	 * To write the data in a excel result sheet cell, using ColumnName. Return true/False.
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @param responsevalue
	 * @return True/False
	 */
	public boolean setCellData(String sheetName,String colName,int rowNum, String ProjectID)
	{
		try
		{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		
		 CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setColor(HSSFColor.DARK_RED.index);
	        style.setFont(font);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		
		
		
		
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    
	    cell.setCellValue(ProjectID);
	    
	    cell.setCellStyle(style);

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();
	    
	    workbook = new XSSFWorkbook(new FileInputStream(path));

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public boolean setCellData(String wsName, String colName, String rowName, String Result){
		try{
			int rowNum = getRowCount(wsName);
			int rowNumber=-1;
			int sheetIndex=workbook.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return false;
			
			sheet=workbook.getSheetAt(sheetIndex);
			int colNum = getColumnCount(wsName);
			int colNumber=-1;
					
			
			XSSFRow Suiterow = sheet.getRow(0);			
			for(int i=0; i<colNum; i++){				
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber=i;					
				}					
			}
			
			if(colNumber==-1){
				return false;				
			}
			
			for (int i=0; i<rowNum-1; i++){
				XSSFRow row = sheet.getRow(i+1);				
				XSSFCell cell = row.getCell(0);	
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String value = cellToString(cell);	
				if(value.equals(rowName)){
					rowNumber=i+1;
					break;
				}
			}		
			
			XSSFRow Row = sheet.getRow(rowNumber);
			XSSFCell cell = Row.getCell(colNumber);
			if (cell == null)
		        cell = Row.createCell(colNumber);			
			
			cell.setCellValue(Result);
			
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	 // find whether sheets exists	
		/**
		 *  To Verify the sheet exists or not.
		 * @param sheetName
		 * @return boolean - true/faluse.
		 */
		public boolean isSheetExist(String sheetName)
		{
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				index=workbook.getSheetIndex(sheetName.toUpperCase());
					if(index==-1)
						return false;
					else
						return true;
			}
			else
				return true;
		}
		
		public boolean createWorkbook(String workbook){
			
			try {
				if ((workbook.split("[.]")[1]).equalsIgnoreCase("xls")) {
					Workbook tempwb = new HSSFWorkbook();
					Sheet sheet1 = tempwb.createSheet("sheet1");
				    FileOutputStream fileOut = new FileOutputStream(workbook);
				    tempwb.write(fileOut);
				    fileOut.close();
				    tempwb.close();
				} else if((workbook.split("[.]")[1]).equalsIgnoreCase("xlsx")){
					 Workbook tempwb = new XSSFWorkbook();
					 Sheet sheet1 = tempwb.createSheet("sheet1");
				 	 FileOutputStream fileOut = new FileOutputStream(workbook);
				 	 tempwb.write(fileOut);
				 	 fileOut.close();
				 	tempwb.close();
				}
				else{
					return false;
				}
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		 	
		 	return true;
		}
		
		public boolean createWorksheet(String worksheet){
			
		 	try {
				String safeName = WorkbookUtil.createSafeSheetName(worksheet); // replace invalid characters with a space (' ')
				Sheet sheet3 = workbook.createSheet(safeName);
				fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		 	return true;
		}
	
		
		/**
		 * Overloaded method. To read a data from a cell based column No. Returns the data from a cell
		 * @param sheetName
		 * @param colNum
		 * @param rowNum
		 * @return cellText - the text value in the specific cell.
		 */
		// returns the data from a cell
		public String getCellData(String sheetName,int colNum,int rowNum)
		{
			try
			{
				if(rowNum <=0)
					return "";
			
			int index = workbook.getSheetIndex(sheetName);

			if(index==-1)
				return "";
			
		
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(colNum);
			if(cell==null)
				return "";
			
			  return cell.getStringCellValue();
		  
		  }
		 
			catch(Exception e)
			{
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colNum +" does not exist  in xlsx";
			}
		}
		
		
		public boolean addRowAt(String worksheet, ArrayList<Object> rowData, int index){
			try {
				int sheetIndex=workbook.getSheetIndex(worksheet);
				
				//create worksheet if not exist
				if(sheetIndex==-1){
					createWorksheet(worksheet);
					sheetIndex=workbook.getSheetIndex(worksheet);
					System.out.println("sheet not exist new sheet craeted.");
		
				}
				
				sheet=workbook.getSheetAt(sheetIndex);
				
				Row row = sheet.createRow(index);
				
				for (int i = 0; i < rowData.size(); i++) {
					row.createCell(i).setCellValue((String)rowData.get(i));
				}
				fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		public boolean addRow(String worksheet, ArrayList<Object> rowData){
			try {
				int sheetIndex=workbook.getSheetIndex(worksheet);
				
				//create worksheet if not exist
				if(sheetIndex==-1){
					createWorksheet(worksheet);
					sheetIndex=workbook.getSheetIndex(worksheet);
					System.out.println("sheet not exist new sheet craeted.");
		
				}
				
				sheet=workbook.getSheetAt(sheetIndex);
				int rowNum = getRowCount(worksheet);
				
				Row row = sheet.createRow(rowNum);
				
				for (int i = 0; i < rowData.size(); i++) {
					row.createCell(i).setCellValue((String)rowData.get(i));
				}
				fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		public ArrayList<Object> getRowAt(String worksheet, int index){
			
			ArrayList<Object> rowData;
			try {	
				int sheetIndex=workbook.getSheetIndex(worksheet);
				
				//create worksheet if not exist
				if(sheetIndex==-1){
					return null;
				}else{
					int colNum = getColumnCount(worksheet);
					if(colNum==-1){
						return null;
					}else{
						
						sheet=workbook.getSheetAt(sheetIndex);
						
						XSSFRow row= sheet.getRow(index);
						
						if(row==null){
							return null;
						}else{
							rowData = new ArrayList<Object>(); 
							for(int j=0;j<colNum;j++){
								XSSFCell cell = row.getCell(j);
								if(cell==null){
									rowData.add("");
									}else{
									//cell.setCellType(Cell.CELL_TYPE_STRING);
										
									String value= cellToString(cell);
									//System.out.println(value);
									rowData.add(value);
								}
									
							}
						}
						
					}
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			return rowData;
		}
}
