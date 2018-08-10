package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("+++class AppTest @BeforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("+++class AppTest @AfterSuite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("\t+++class AppTest @BeforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("\t+++class AppTest @AfterTest");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("\t\t+++class AppTest @BeforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("\t\t+++class AppTest @AfterClass");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t+++class AppTest @BeforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("\t\t\t+++class AppTest @AfterMethod");
	}

	@Test
	public void testApp1() {
		System.out.println("\t\t\t\t+++class AppTest @Test testApp1()");
		Assert.assertTrue(true);
	}

	@Test
	public void testApp2() {
		System.out.println("\t\t\t\t+++class AppTest @Test testApp2()");
		Assert.assertTrue(true);
	}

}
