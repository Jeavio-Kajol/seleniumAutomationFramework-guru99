package com.qa.guru99.helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class ScreenshotUtils {
		public static String takeScreenshot(WebDriver driver, String testName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destPath = "test-output/ExtentReports/failed-testcases/screenshots/" + testName + ".png";
		try {
			FileUtils.copyFile(srcFile, new File(destPath));
		} catch (IOException e) {
			System.out.println("Error copying the Snapshot to the destination folder" + e.getMessage());
			e.printStackTrace();
		}
		return destPath;

	}

}
