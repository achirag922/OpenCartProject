package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = "./testData/Opencart_loginData.xlsx"; // Taking Excel file from testData
		ExcelUtils xlUtils = new ExcelUtils(path); // Creating an Object for xlUtility

		int totalRowCount = xlUtils.getRowCount("Sheet1");
		int totalCellCount = xlUtils.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalRowCount][totalCellCount]; // Created two dimensional array which can
																			// store cell data

		for (int i = 1; i <= totalRowCount; i++) { // Read the data from Excel storing in two dimensional array
			for (int j = 0; j < totalCellCount; j++) { // i is rows and j is columns
				loginData[i - 1][j] = xlUtils.getCellData("Sheet1", i, j);
			}
		}
		return loginData; // Returning two dimensional array

	}

}
