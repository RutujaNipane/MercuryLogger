package com.model.MercuryLogger;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MercuryLoggerTest 
{
	public WebDriver driver;
	static Logger logger=Logger.getLogger(MercuryLoggerTest.class);
	@Test(priority=1)
	  public void loginwithvalidds()
	  {
	    PropertyConfigurator.configure("G:\\OxygenWorkspce\\MercuryLogger\\src\\test\\resources\\log4j.properties");
	    driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("rutuja");
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rutuja");
	    driver.findElement(By.xpath("//input[@name='login']")).click();
	    driver.findElement(By.linkText("SIGN-OFF")).click();
	    logger.info("User has successfully login into Mercury Tours application");
	    logger.debug("User has successfully login into Mercury Tours application");
	    
	  }
	  
	  @Test(priority=2)
	  public void loginwithinvalidds()
	  {
	    PropertyConfigurator.configure("G:\\OxygenWorkspce\\MercuryLogger\\src\\test\\resources\\log4j.properties");
	    driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("rutu");
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rutu");
	    driver.findElement(By.xpath("//input[@name='login']")).click();
	    logger.error("User error message");
	    
	  }
	  @BeforeMethod
	  public void getAllCookies() 
	  {
		  Set <Cookie> cookies=driver.manage().getCookies();
		  for(Cookie cookie:cookies)
		  {
			  System.out.println(cookie.getName());
		  }
		  
	  }

	  @AfterMethod
	  public void captureScreenshot() throws IOException 
	  {
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFileToDirectory(src, new File("G:\\OxygenWorkspce\\MercuryLogger\\src\\test\\resources\\Screenshots\\"));
		
	  }

	  @BeforeClass
	  public void maximizeBrowser() 
	  {
		  driver.manage().window().maximize();
		  logger.info("Maximize browser");
	  }

	  @AfterClass
	  public void deleteAllCookies() 
	  {
		  driver.manage().deleteAllCookies();
		  logger.warn("warning message");
	  }

	  @BeforeTest
	  public void enterApplicationURL() 
	  {
		  driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		  logger.info("URL is entered successfully");
	  }

	  @AfterTest
	  public void dbConnectionClosed() 
	  {
		  logger.info("dbConnection is closed");
	  }

	  @BeforeSuite
	  public void openBrowser() 
	  {
		  System.setProperty("webdriver.chrome.driver","G:\\TESTING\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
		  logger.info("Chrome browser is opened successfully");
		  
	  }

	  @AfterSuite
	  public void closeBrowser()
	  {
		  driver.close();
	  }

}
