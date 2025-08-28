package com.qa.guru99.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.guru99.helper.WebDriverWaitUtils;
import com.qa.guru99.base.TestBase;

public class RegistrationPage extends TestBase {
	WebDriver driver;

	@FindBy(name = "emailid")
	private WebElement emailField;

	@FindBy(name = "btnLogin")
	private WebElement submitButton;

	@FindBy(xpath = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userId;

	@FindBy(xpath = "//td[text()= 'Password :']/following-sibling::td")
	private WebElement password;
	
	@FindBy(xpath = "//a[text()='Bank Project']")
	private WebElement bankProjectLinktext;
	
	

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void registerEmail(String email) {
	    LOGGER.info("Registering email: {}", email);
	    try {
	        WebDriverWaitUtils.waitForTheVisibilityOfElement(driver, Duration.ofSeconds(10), emailField);
	        emailField.sendKeys(email);
	        submitButton.click();
	        LOGGER.info("Email submitted successfully");
	    } catch (Exception e) {
	        LOGGER.error("Unable to click on Submit button: {}", e.getMessage());
	    }
	}

	public String getUserId() {
	    try {
	        WebDriverWaitUtils.waitForTheVisibilityOfElement(driver, Duration.ofSeconds(10), userId);
	        String id = userId.getText();
	        LOGGER.info("Fetched userId: {}", id);
	        return id;
	    } catch (Exception e) {
	        LOGGER.error("Error fetching userId: {}", e.getMessage());
	        return " ";
	    }
	}

	public String getPassword() {
		try {
			WebDriverWaitUtils.waitForTheVisibilityOfElement(driver, Duration.ofSeconds(10), password);
			String pwd = password.getText();
			 LOGGER.info("Fetched password: {}", pwd);
			 return pwd;
		} catch (Exception e) {
			LOGGER.error("Error fetching password: {}", e.getMessage());
			return " ";
		}
	}
	
	public void clickBankProjectLinktext() {
		bankProjectLinktext.click();
	}

}
