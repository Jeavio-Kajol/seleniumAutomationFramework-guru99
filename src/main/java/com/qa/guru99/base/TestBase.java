package com.qa.guru99.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.time.Duration;

@Listeners(com.qa.guru99.helper.ITestListenerUtils.class)
public abstract class TestBase {

	protected WebDriver driver;
	protected Properties properties;
	protected final Logger LOGGER;

	// Constructor where we initialize the logger for the current class
	public TestBase() {
		this.LOGGER = LogManager.getLogger(this.getClass());
	}

	
	// Method to get the logger instance for the current class
	public Logger getLogger() {
		return this.LOGGER;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void loadConfig() {
		properties = new Properties();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		try (FileInputStream fis = new FileInputStream(filePath)) {

			properties.load(fis);

		} catch (IOException e) {
			LOGGER.info("Error loading the config.properties file: ", e);
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void initializeBrowser() {
		if (properties == null) {
			loadConfig(); // Fallback in case @BeforeSuite did not execute
		}
		String browser = properties.getProperty("browser");
		if (browser == null) {
			throw new IllegalStateException("Browser property is not set in config.properties");
		}
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			// ChromeOptions - Adding download directory configuration
			
			String downloadPath = System.getProperty("user.dir") + "\\test-output\\file_downloads";
			
			HashMap<String, Object> chromePrefs = new HashMap<>();			
			chromePrefs.put("download.default_directory", downloadPath);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options); // Initialize driver with ChromeOptions

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//FirefoxOptions options = new FirefoxOptions();
			//options.setBinary("C:\\Users\\Kajol\\AppData\\Local\\Mozilla Firefox\\firefox.exe"); // <-- exact path to your firefox.exe
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			 //System.setProperty("webdriver.edge.driver", "C:\\Users\\Kajol\\guru99_Selenium_Java_framework\\Selenium-Automation-Framework-herokuapp\\drivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Unsupported browser. " + browser);
		}

		int implicitWait = Integer.parseInt(System.getProperty("implicitWait", "10"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));

	}

	@AfterClass
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
