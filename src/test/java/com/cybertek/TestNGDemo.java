package com.cybertek;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGDemo {
	@BeforeClass
	public void setUpClass(){
		System.out.println("Run once before everthing in this class");
	}
	@BeforeMethod
	public void setUpMethod(){
		System.out.println("Runs before any method");
	}
	@Test
	public void testone(){
		System.out.println("First one");
	}
	@Test
	public void testsecond(){
		System.out.println("Second one");
	}
	@AfterMethod
	public void teardownMethod(){
		System.out.println("Runs after every method");
	}
	@AfterClass
	public void tearDownMethod(){
		System.out.println("Runs after everything in this class");
	}

}
