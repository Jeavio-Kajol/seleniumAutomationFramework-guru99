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
import com.qa.guru99.base.TestBase;

public class NewCustomerTest extends TestBase {
	private LoginPage loginPage;
	private NewCustomerPage newCustomerPage;
	private RegistrationPage regPage;
	private String userId;
    private String pwd;
    
    @BeforeClass
    public void setUpUser() throws InterruptedException {
        LOGGER.info("Setting up user for NewCustomerTest");
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

    @Test(dataProvider = "getCustomerData")
    public void addCustomer(String name, String gender, String dob, String address, String city, 
                            String state, String pin, String mobile, String email) throws InterruptedException {
        LOGGER.info("Adding new customer with name: {}, gender: {}", name, gender);

        String uniqueEmail = email.split("@")[0] + System.currentTimeMillis() + "@mail.com";
        LOGGER.debug("Generated unique email: {}", uniqueEmail);

        newCustomerPage.fillCustomerForm(name, gender, dob, address, city, state, pin, mobile, uniqueEmail);
        newCustomerPage.submitForm();

        String errorText = newCustomerPage.getErrorMessage();
        LOGGER.warn("Error received after form submission: {}", errorText);
        Assert.assertEquals("This page isn’t working", errorText);

        driver.navigate().back();
        driver.navigate().refresh();
    }

	@DataProvider(name = "getCustomerData")
	public Object[][] getCustomerData() throws IOException {
		return ExcelReader.readTestData("C:\\Users\\Kajol\\OneDrive\\Documents\\Guru99_PositiveTestCaseData.xlsx");
	}

}
