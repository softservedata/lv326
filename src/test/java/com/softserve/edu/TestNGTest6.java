package com.softserve.edu;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGTest6 {
	
	@BeforeClass
	public void beforeClass(ITestContext context) {
		System.out.println("\t\t+++class TestNGTest6 @BeforeClass");
		HashMap<String, String> allParameters = new HashMap<String, String>(
				context.getCurrentXmlTest().getAllParameters());
		for (String key : allParameters.keySet()) {
			System.out.println("\t\t*** parameter: key=" + key + " value=" + allParameters.get(key));
		}
	}
	
	@Test
	@Parameters(value="number")
	public void parameterIntTest(int number) {
		System.out.println("\t\t+++Parameterized Number is: " + number);
		System.out.println("\t\t+++surefire.info = " + System.getProperty("surefire.info"));
		//
		System.out.println("\t\t+++System.getenv() = " + System.getenv());
	}
}
