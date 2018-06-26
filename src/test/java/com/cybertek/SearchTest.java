package com.cybertek;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {
	@Test
	public void amazonSearchOne() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://amazon.com");
		String str="selenium testing tools cookbook ";
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(str+Keys.ENTER);
		String xpath= "//h2[@class='a-size-medium s-inline  s-access-title  a-text-normal'][.='Selenium Testing Tools Cookbook - Second Edition']";
//		Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java OCA book"+Keys.ENTER);
		
		try{
			Assert.assertFalse(driver.findElement(By.xpath(xpath)).isDisplayed());
		}catch(NoSuchElementException e){
			//if the element is not in the html at all exception will be thrown
			//since are verify sth does not exist, nosucheElementExceptionmeans test should pass
			e.printStackTrace();
		}
		
		
		//driver.close();
		
	}
}