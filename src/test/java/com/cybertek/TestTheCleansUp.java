package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestTheCleansUp {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = (WebDriver) new ChromeDriver();
	}

	@Test
	public void searchGoogle() {

		driver.get("https://google.com");
		driver.findElement(By.id("q")).sendKeys("selenium cookbook" + Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("selenium cookbook"));
		
	}

	@Test
	public void searchAmazon() {

		driver.get("https://amazon.com");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("fathers day gift" + Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("fathers day gift"));

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
