package ui.pageobjects.pagehelpers;

import java.util.ArrayList;


public class ExcelUtility {

	public Object[][] getData(XlsReader workbook,String worksheet){
		
		return workbook.retrieveTestData(worksheet);
	}

	public boolean writeResult(XlsReader workbook,String worksheet,String colName, String testCase, String result){
		
		return workbook.setCellData(worksheet, colName, testCase, result);
	}

	public boolean getToRunFlag(XlsReader workbook,String worksheet,String testCase) {
		if(workbook.retrieveToRunFlag(worksheet, "flag", testCase).equalsIgnoreCase("y")){
		
			return true;
		}else{
			
			return false;
		}
			
	}
	public ArrayList<ArrayList<Object>> getDataAsArrayList(XlsReader workbook,String worksheet){
		
		return workbook.retrieveTestDataAsArrayList(worksheet);
	}
}
