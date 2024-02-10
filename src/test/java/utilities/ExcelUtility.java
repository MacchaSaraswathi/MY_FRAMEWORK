package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	private static final String FILE_PATH = "src/test/resources/TestData.xlsx";

    public static Object[][] readTestData() {
        Object[][] testData = null;
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            testData = new Object[lastRowNum][3]; // Assuming there are three columns: Username, Password, Status
            for (int i = 1; i <= lastRowNum; i++) { // Start from 1 to skip header row
                Row row = sheet.getRow(i);
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                String status = row.getCell(2).getStringCellValue();
                testData[i - 1] = new Object[]{username, password, status};
            }
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        }
        return testData;
    }
}

	

	    
	
	

	
	
	
	