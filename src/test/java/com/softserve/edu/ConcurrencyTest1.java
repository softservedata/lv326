package com.softserve.edu;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConcurrencyTest1 {
	private Map<String, String> data;

	@BeforeClass
	void setUp() throws Exception {
		data = new HashMap<String, String>();
	}

	@AfterClass
	void tearDown() throws Exception {
		data = null;
	}

	@Test(threadPoolSize = 3, invocationCount = 4, invocationTimeOut = 10000)
	public void testMapOperations() throws Exception {
		System.out.println("+++start ID= " + Thread.currentThread().getId());
		data.put("A_" + String.valueOf(System.currentTimeMillis()),
				String.valueOf(Thread.currentThread().getId()));
		data.put("B_" + String.valueOf(System.currentTimeMillis()),
				String.valueOf(Thread.currentThread().getId()));
		data.put("C_" + String.valueOf(System.currentTimeMillis()),
				String.valueOf(Thread.currentThread().getId()));
		for (Map.Entry<String, String> entry : data.entrySet()) {
			System.out.println(entry);
			Thread.sleep(5);
		}
		data.clear();
		System.out.println("+++done ID= " + Thread.currentThread().getId());
	}
}