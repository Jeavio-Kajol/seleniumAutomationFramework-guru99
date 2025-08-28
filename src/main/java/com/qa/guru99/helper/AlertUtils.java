package com.qa.guru99.helper;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertUtils {
	private static Alert alert;

	public static String alertGetText(WebDriver driver) {
	    try {
	        alert = waitForAlert(driver, Duration.ofSeconds(10));
	        return alert.getText();
	    } catch (NoAlertPresentException e) {
	        System.out.println("No alert present: " + e.getMessage());
	        return null;
	    }
	}

	public static Alert waitForAlert(WebDriver driver, Duration timeout) {
		return WebDriverWaitUtils.checkIfAlertIsPresent(driver, timeout);
	}

	public static void alertDismiss(WebDriver driver) {
		try {
			alert = waitForAlert(driver, Duration.ofSeconds(5));
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert present: " + e.getMessage());

		}
	}

	public static void alertAccept(WebDriver driver) {
	    try {
	        alert = waitForAlert(driver, Duration.ofSeconds(5));
	        alert.accept();
	    } catch (NoAlertPresentException e) {
	        System.out.println("No such alert window." + e.getMessage());
	    }
	}
	
	public static void alertSendkeys(WebDriver driver, String str) {
	    try {
	        alert = waitForAlert(driver, Duration.ofSeconds(5));
	        alert.sendKeys(str);
	    } catch (NoAlertPresentException e) {
	        System.out.println("No such alert window." + e.getMessage());
	    }
	}

}
