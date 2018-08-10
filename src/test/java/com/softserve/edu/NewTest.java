package com.softserve.edu;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("+++class NewTest @BeforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("+++class NewTest @AfterSuite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("\t+++class NewTest @BeforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("\t+++class NewTest @AfterTest");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("\t\t+++class NewTest @BeforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("\t\t+++class NewTest @AfterClass");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t+++class NewTest @BeforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("\t\t\t+++class NewTest @AfterMethod");
	}

	@Test
	public void test1() {
		System.out.println("\t\t\t\t+++class NewTest @Test test1()");
	}

	@Test
	public void test2() {
		System.out.println("\t\t\t\t+++class NewTest @Test test2()");
	}

}
