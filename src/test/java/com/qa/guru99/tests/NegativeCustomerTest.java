package com.qa.guru99.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.guru99.pages.ExcelReader;
import com.qa.guru99.pages.LoginPage;
import com.qa.guru99.pages.NewCustomerPage;
import com.qa.guru99.pages.RegistrationPage;
import com.qa.guru99.base.TestBase;;

public class NegativeCustomerTest extends TestBase {
	private LoginPage loginPage;
	private NewCustomerPage newCustomerPage;
	private RegistrationPage regPage;
	private String userId;
	private String pwd;

	@BeforeClass
	public void setUpUser() throws InterruptedException {
	    LOGGER.info("Setting up user for NegativeCustomerTest");
	    regPage = new RegistrationPage(getDriver());
	    String tempEmail = "test" + System.currentTimeMillis() + "@mailinator.com";
	    LOGGER.info("Registering with email: {}", tempEmail);
	    regPage.registerEmail(tempEmail);

	    userId = regPage.getUserId();
	    pwd = regPage.getPassword();
	    LOGGER.info("Received userId: {}, password: {}", userId, pwd);

	    regPage.clickBankProjectLinktext();
	    LOGGER.info("Logging in to Guru99");
	    loginPage = new LoginPage(getDriver());
	    loginPage.login(userId, pwd);

	    newCustomerPage = new NewCustomerPage(getDriver());
	    newCustomerPage.openNewCustomerForm();
	}

	@Test(dataProvider = "getFieldValidationData")
	public void validateFieldError(String fieldName, String invalidValue, String expectedError) {
	    LOGGER.info("Validating field: {}, invalid value: {}", fieldName, invalidValue);

	    if (invalidValue == null || invalidValue.isEmpty()) {
	        newCustomerPage.blurField(fieldName);
	    } else {
	        newCustomerPage.typeInField(fieldName, invalidValue);
	    }

	    String actualError = newCustomerPage.getFieldError(fieldName);
	    LOGGER.debug("Expected error: '{}', Actual error: '{}'", expectedError, actualError);
	    Assert.assertEquals(actualError, expectedError, 
	        "Mismatch for field: " + fieldName + " with value: " + invalidValue);
	    driver.navigate().refresh();
	}

	@DataProvider(name = "getFieldValidationData")
	public Object[][] getNegativeCustomerData() throws IOException {
		return ExcelReader.readTestData("C:\\Users\\Kajol\\OneDrive\\Documents\\Guru99_NegativeTestData.xlsx");
	}

}
