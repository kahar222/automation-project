package com.cybertek;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AssertionDemo {
	@AfterMethod
	public void tearDown(){
		System.out.println("Cleaning up");
	}
	@Test
	public void testOne(){
		System.out.println("asserting first");	
		Assert.assertTrue(false);		
		System.out.println("done asserting first");
	}@Test
	public void testSecond(){
		System.out.println("asserting second");	
		Assert.assertTrue(false);		
		System.out.println("done asserting second");
	}
}
