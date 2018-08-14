package com.softserve.tra;

public class Two3 {
	private One3 one;

	public Two3(One3 one) {
		this.one = one;
	}

	public double resume(double arg0) {
		return 1 + one.calc(arg0);
	}

}