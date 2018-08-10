package com.softserve.edu;

import org.testng.annotations.Test;

public class TestNGTest4 {
	
	@Test(timeOut = 5020)
	public void infinity() {
		long currentTime = System.currentTimeMillis();
		long startTime = currentTime; 
		long workTime = currentTime;
		while (currentTime - startTime < 5000) {
			currentTime = System.currentTimeMillis();
			if (currentTime - workTime > 500) {
				System.out.println("Working = " + (currentTime - startTime));
				workTime = currentTime;
			}
		}
		System.out.println("Working = " + (currentTime - startTime));
	}
}
