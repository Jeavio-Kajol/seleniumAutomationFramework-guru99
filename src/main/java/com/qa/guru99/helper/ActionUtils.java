package com.qa.guru99.helper;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {

	protected static WebDriver driver;
	protected static Actions action;

	public static void alertContextClickAndHold(WebDriver driver, WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).clickAndHold(element).perform();
	}

	public static void alertClickAndHold(WebDriver driver, WebElement element) {
		action = new Actions(driver);
		action.clickAndHold(element).perform();
	}

	public static void releaseClick(WebDriver driver) {
		action = new Actions(driver);
		action.release().perform();
	}

	public static void elementDragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
	}

	public static void dragAndDropWithWait(WebDriver driver, WebElement source, WebElement target, Duration waitTime) {
		action = new Actions(driver);
		WebDriverWaitUtils.waitForTheVisibilityOfElement(driver, waitTime, source);
		elementDragAndDrop(driver, source, target);
	}

	public static void customElementDragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		action = new Actions(driver);
		action.clickAndHold(source).moveToElement(target).release(target).build().perform();
	}

	public static void moveToElement(WebDriver driver, WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	public static void moveToElements(WebDriver driver, WebElement element, Keys keys) {
		action = new Actions(driver);		
		action.click(element) // Ensure the slider is selected first
        .sendKeys(keys) // Send arrow key press
        .perform();
	}
}
