package utility;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	static String projectPath = System.getProperty("user.dir");
	static String query = null;

	public static void getRowCount() {
		try {
			workBook = new XSSFWorkbook(projectPath + "\\data_driven\\data.xlsx");
			sheet = workBook.getSheet("Sheet1");
			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println(rowCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	public static String getQuires(int row) {
		try {
			workBook = new XSSFWorkbook(projectPath + "\\data_driven\\data.xlsx");
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			query = sheet.getRow(row).getCell(0).getStringCellValue();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return query;
	}
}
