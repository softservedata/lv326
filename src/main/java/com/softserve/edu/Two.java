package com.softserve.edu;

public class Two {
	private One one;

	public Two() {
		one = new One();
	}

	public double resume(double arg0) {
		return 1 + one.calc(arg0);
	}
	
}
