package com.qa.guru99.pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.guru99.helper.WebDriverWaitUtils;
import com.qa.guru99.base.TestBase;

public class NewCustomerPage extends TestBase {
	@FindBy(linkText = "New Customer")
	private WebElement newCustomerLink;

	@FindBy(name = "name")
	private WebElement customerName;

	@FindBy(xpath = "//*[@type='radio'][1]")
	private WebElement genderMale;

	@FindBy(xpath = "//*[@type='radio'][2]")
	private WebElement genderFemale;

	@FindBy(id = "dob")
	private WebElement dob;

	@FindBy(name = "addr")
	private WebElement address;

	@FindBy(name = "city")
	private WebElement city;

	@FindBy(name = "state")
	private WebElement state;

	@FindBy(name = "pinno")
	private WebElement pin;

	@FindBy(name = "telephoneno")
	private WebElement mobile;

	@FindBy(name = "emailid")
	private WebElement email;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//input[@name='sub']")
	private WebElement submitButton;

	@FindBy(xpath = "//p[@class='heading3']")
	private WebElement successMessage;

	@FindBy(xpath = "//span[text()='This page isn’t working']")
	private WebElement errorMessage;

	// ---- Invalid Testcases webelements

	@FindBy(xpath = "//label[@id='message']")
	private WebElement nameError;

	@FindBy(xpath = "//label[@id='message4']")
	private WebElement cityError;

	@FindBy(xpath = "//label[@id='message5']")
	private WebElement stateError;

	@FindBy(xpath = "//label[@id='message6']")
	private WebElement pinError;

	@FindBy(xpath = "//label[@id='message7']")
	private WebElement mobileError;

	@FindBy(xpath = "//label[@id='message9']")
	private WebElement emailError;

	public NewCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openNewCustomerForm() {
		LOGGER.info("Opening New Customer form");
		WebDriverWaitUtils.waitForElementToBeClickable(driver, Duration.ofSeconds(10), newCustomerLink);
		newCustomerLink.click(); // for chrome
		// for Firefox - ((JavascriptExecutor)
		// driver).executeScript("arguments[0].click();", newCustomerLink);

	}

	public void selectGender(String gender) {
		LOGGER.info("Selecting gender: {}", gender);
		boolean isMaleSelected = genderMale.isSelected();
		boolean isFemaleSelected = genderFemale.isSelected();
		if (gender.equalsIgnoreCase("male") && !isMaleSelected) {
			genderMale.click();

		} else if (gender.equalsIgnoreCase("female") && !isFemaleSelected) {
			genderFemale.click();
		}
	}

	public void fillCustomerForm(String name, String gender, String birthdate, String addr, String cityName,
			String stateName, String pinCode, String phone, String emailId) {
		LOGGER.info("Filling customer form for name: {}", name);
		customerName.sendKeys(name);
		selectGender(gender);
		LOGGER.info("Selecting Date of Birth: {}", birthdate);
		dob.sendKeys(birthdate);
		LOGGER.info("Filling customer form for address: {}", addr);
		address.sendKeys(addr);
		LOGGER.info("Filling customer form for city: {}", cityName);
		city.sendKeys(cityName);
		LOGGER.info("Filling customer form for state: {}", stateName);
		state.sendKeys(stateName);
		LOGGER.info("Filling customer form for pin: {}", pinCode);
		pin.sendKeys(pinCode);
		LOGGER.info("Filling customer form for phone: {}", phone);
		mobile.sendKeys(phone);
		LOGGER.info("Filling customer form for email: {}", emailId);
		email.clear();

		email.sendKeys(emailId);
	}

	public void typeInField(String fieldName, String value) {
		switch (fieldName.toLowerCase()) {
		case "name":
			customerName.sendKeys(value);
			break;
		case "gender":
			selectGender(value);
			break;
		case "dob":
			dob.sendKeys(value);
			break;
		case "address":
			address.sendKeys(value);
			break;
		case "city":
			city.sendKeys(value);
			break;
		case "state":
			state.sendKeys(value);
			break;
		case "pin":
			pin.sendKeys(value);
			break;
		case "mobile":
			mobile.sendKeys(value);
			break;
		case "email":
			email.sendKeys(value);
			break;
		default:
			throw new IllegalArgumentException("Unknow field: " + fieldName);
		}

	}

	public String getFieldError(String fieldName) {
		switch (fieldName.toLowerCase()) {
		case "name":
			return nameError.getText();
		case "gender":
			return "";
		case "dob":
			return "";
		case "address":
			return "";
		case "city":
			return cityError.getText();
		case "state":
			return stateError.getText();
		case "pin":
			return pinError.getText();
		case "mobile":
			return mobileError.getText();
		case "email":
			return emailError.getText();
		default:
			throw new IllegalArgumentException("Unknow field: " + fieldName);
		}
	}

	private WebElement getFieldElement(String fieldName) {
		switch (fieldName.toLowerCase()) {
		case "name":
			return customerName;
		case "address":
			return address;
		case "city":
			return city;
		case "state":
			return state;
		case "pin":
			return pin;
		case "mobile":
			return mobile;
		case "email":
			return email;
		default:
			throw new IllegalArgumentException("Unknown field: " + fieldName);
		}
	}

	public void blurField(String fieldName) {
		WebElement targetField = getFieldElement(fieldName);
		targetField.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].blur();", targetField);

	}

	public void submitForm() {
		LOGGER.info("Submitting customer form");
		submitButton.click();
	}

	public String getSuccessMessage() {
		return successMessage.getText();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

}