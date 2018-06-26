package com.cybertek;

/*
• Goto https://mockaroo.com/
• Specify Field names, Type, Rows count, format
• Download data in CSV format
• Read all data from the file and load to collection
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public static boolean isPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Test(priority = 1)
	public void test() {

		driver.get("https://mockaroo.com/");
		driver.getTitle();
		Assert.assertTrue(driver.getTitle()
				.contains("Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel"));
		String brand = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		Assert.assertTrue(brand.equalsIgnoreCase("Mockaroo"));
		String tagLine = driver.findElement(By.xpath("//div[@class='tagline']")).getText();
		Assert.assertTrue(tagLine.equalsIgnoreCase("realistic data generator"));
		List<WebElement> elements = driver
				.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));

		for (WebElement webElement : elements) {
			webElement.click();
		}

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText()
				.equalsIgnoreCase("Field name"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-type']")).getText()
				.equalsIgnoreCase("Type"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-options']")).getText()
				.equalsIgnoreCase("Options"));

		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Add another field')]")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1000']")).getAttribute("value").equals("1000"));

		Select format = new Select(driver.findElement(By.xpath("//select[@id='schema_file_format']")));
		Assert.assertTrue(format.getFirstSelectedOption().getText().equalsIgnoreCase("csv"));

		Select lineEnding = new Select(driver.findElement(By.xpath("//select[@id='schema_line_ending']")));
		Assert.assertTrue(lineEnding.getFirstSelectedOption().getText().equalsIgnoreCase("Unix (LF)"));

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='schema_include_header']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='schema_bom']")).isSelected());

	}

	@Test(priority = 2)
	public void create() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Add another field')]")).click();
		driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[7]")).sendKeys("City");
		driver.findElement(By.xpath("(//input[@class='btn btn-default'])[7]")).click();

		Assert.assertTrue(isPresent(driver, (By.xpath("//h3[@class='modal-title']"))));
		WebElement web = driver.findElement(By.xpath("//input[@id='type_search_field']"));
		Thread.sleep(3000);
		web.sendKeys("City");
		driver.findElement(By.xpath("//div[@class='type-name']")).click();
		// ------------------------------------------------------------------
		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[contains(text(),'Add another field')]")).click();
		driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[8]")).clear();
		driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[8]")).sendKeys("Country");
		driver.findElement(By.xpath("(//input[@class='btn btn-default'])[8]")).click();
		Thread.sleep(1000);
		Assert.assertTrue(isPresent(driver, (By.xpath("//h3[@class='modal-title']"))));
		WebElement web2 = driver.findElement(By.xpath("//input[@id='type_search_field']"));
		Thread.sleep(3000);
		web2.sendKeys("Country");
		driver.findElement(By.xpath("//div[@class='type-name']")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@id='download']")).click();

		Thread.sleep(5000);
	}

	@Test(priority = 3)
	public void runner() throws IOException {

		List<String> countries = new ArrayList<String>();

		FileReader fr = new FileReader("/Users/anaf/Downloads/MOCK_DATA.csv");
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		int count = 0;
		while ((line = br.readLine()) != null) {
			countries.add(line);
			count++;
		}
		String[] arr = countries.get(0).split(",");
		System.out.println(arr[0] + " " + arr[1]);

		Assert.assertTrue((arr[0] + " " + arr[1]).equals("City Country"));

		Assert.assertTrue(count == 1001);
		List<String> countries1 = new ArrayList<String>();
		List<String> cities = new ArrayList<String>();
		for (int i = 1; i < countries.size(); i++) {
			String[] arr1 = countries.get(i).split(",");
			cities.add(arr1[0]);
			countries1.add(arr1[1]);
		}
		// System.out.println(cities);

		Collections.sort(cities);
		Collections.sort(countries);
		int MAX = 0;
		String longCity = "";
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).length() > MAX)
				MAX = cities.get(i).length();
			longCity = cities.get(i);
		}
		System.out.println(longCity + "  " + MAX);

		int MIN = MAX;
		String shortCity = "";
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).length() < MIN)
				MIN = cities.get(i).length();
			shortCity = cities.get(i);
		}
		System.out.println(shortCity + " " + MIN);
		
		List<String> uniqueCities = new ArrayList<String>();
		for (String city : cities) {
			if(!uniqueCities.contains(city))
				uniqueCities.add(city);
		}
		
		HashSet<String> citySet = new HashSet<String>(cities);
		Assert.assertEquals(uniqueCities.size(), citySet.size());
		
		List<String> uniqueCountries = new ArrayList<String>();
		for (String country : countries) {
			if(!uniqueCountries.contains(country))
				uniqueCountries.add(country);
		}
		
		Assert.assertEquals(uniqueCities.size(), citySet.size());
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}

}