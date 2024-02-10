package utilities;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility1 {
	
	    private static final String path = "src/test/resources/TestData.xlsx";
	    private static final String Sheet = "TestData";

	    @BeforeClass
	    public void setUp() {
	        System.out.println("Initializing Test Data...");
	    }

	    @Test
	    public void testWriteAndReadTestData() {
	        Map<String, Object[]> data = new TreeMap<>();
	        data.put("1", new Object[] { "UserName", "Password", "Status" });
	        data.put("2", new Object[] { "mercury", "mercury123", "fail" });
	        data.put("3", new Object[] { "itest@test.com", "itest@test", "pass" });
	        data.put("4", new Object[] { "admin", "admin123", "fail" });
	        data.put("5", new Object[] { "siri@gmail.com", "siri", "pass" });

	        writeTestData(data);

	        Map<String, Object[]> testData = readTestData();
	        System.out.println("Test Data from Excel:");
	        for (Map.Entry<String, Object[]> entry : testData.entrySet()) {
	            String key = entry.getKey();
	            Object[] values = entry.getValue();
	            System.out.print("Row " + key + ": ");
	            for (Object value : values) {
	                System.out.print(value + "\t");
	            }
	            System.out.println();
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        System.out.println("Cleaning up...");
	    }

	    @BeforeMethod
	    public void beforeMethod() {
	        System.out.println("Executing Test Method...");
	    }

	    @AfterMethod
	    public void afterMethod() {
	        System.out.println("Completed Test Method.");
	    }

	    private static Map<String, Object[]> readTestData() {
	        Map<String, Object[]> data = new TreeMap<>();

	        try (FileInputStream file = new FileInputStream(path)) {
	            @SuppressWarnings("resource")
				XSSFWorkbook workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheet(Sheet);

	            int rowCount = sheet.getPhysicalNumberOfRows();
	            for (int i = 0; i < rowCount; i++) {
	                Row row = sheet.getRow(i);
	                int colCount = row.getPhysicalNumberOfCells();
	                Object[] rowData = new Object[colCount];
	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    rowData[j] = cell.toString();
	                }
	                data.put(String.valueOf(i + 1), rowData);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return data;
	    }

	    private static void writeTestData(Map<String, Object[]> data) {
	        try (FileOutputStream writer = new FileOutputStream(path)) {
	            @SuppressWarnings("resource")
				XSSFWorkbook workbook = new XSSFWorkbook();
	            XSSFSheet sheet = workbook.createSheet(Sheet);

	            Set<String> keyset = data.keySet();
	            int rownum = 0;

	            for (String key : keyset) {
	                Row row = sheet.createRow(rownum++);
	                Object[] objArr = data.get(key);

	                int cellnum = 0;

	                for (Object obj : objArr) {
	                    Cell cell = row.createCell(cellnum++);

	                    if (obj instanceof String)
	                        cell.setCellValue((String) obj);
	                    else if (obj instanceof Integer)
	                        cell.setCellValue((Integer) obj);
	                }
	            }

	            workbook.write(writer);
	            System.out.println("TestData written successfully on disk.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}



	    


