package com.qa.guru99.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static Object[][] readTestData(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook wk = new XSSFWorkbook(fis);
		Sheet sheet = wk.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Object[][] data = new Object[rows - 1][cols];// Adjusting for header
		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < cols; j++) {
				Cell cell = row.getCell(j);
                if (cell == null) {
                    data[i - 1][j] = "";
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    data[i - 1][j] = DateUtil.isCellDateFormatted(cell) ?
                            sdf.format(cell.getDateCellValue()) :
                            String.valueOf((long) cell.getNumericCellValue());
                } else {
                    data[i - 1][j] = cell.toString();
                }
            }
        }
		wk.close();
        return data;
    }
}