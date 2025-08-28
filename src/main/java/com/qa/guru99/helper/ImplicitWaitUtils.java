package com.qa.guru99.helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class ImplicitWaitUtils {
	protected WebDriver driver;

	public static void waitForThePageGetLoaded(WebDriver driver, Duration duration) {
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(duration); // Set the implicit wait
		} else {
			System.out.println("WebDriver is not initialized.");
		}
	}

}
