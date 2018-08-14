package com.softserve.train;

public final class One2 {
//public class One2 {

	private static volatile One2 instance = null;

	private One2() {
	}

	public static One2 get() {
		if (instance == null) {
			synchronized (One2.class) {
				if (instance == null) {
					instance = new One2();
				}
			}
		}
		return instance;
	}

	public double calc(double arg0) {
		return 2 * arg0;
	}
}
