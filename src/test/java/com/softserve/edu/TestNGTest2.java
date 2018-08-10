package com.softserve.edu;

import org.testng.annotations.Test;

public class TestNGTest2 {

	@Test(expectedExceptions = ArithmeticException.class)
	public void divisionWithException() {
		System.out.println("done");
		// int i = 1 / 0;
	}

}
