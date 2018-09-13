package com.softserve.edu;

import java.lang.reflect.Field;

class WithPrivateFinalField {

	private int i = 1;
	//private String s3 = "String S";
	private String s3 = new String("String S");
	private final String s = "String S"; // Cache JVM String intern. // Not Modify
	//private final String s = new String("String S");
	//private final String s;
	private String s2 = "String S2";
	private final StringBuilder sb = new StringBuilder("String SB");
	private final Object obj = new Object();
	private final int j = 111;           // Not Modify
	// private final int j = 130;          // Not Modify
	//private final int j = -30000; // JVM Key cache? // Not Modify
	//private final int j;
	//private final int j = new Integer(111);
	private final Integer k = 10;
	// private final Integer k = new Integer(10);
	//private static int m = 12;
	//private static final int m = 12;
	//private static final int m;
	private static int m;
	//private static final int m;
	//private static final Integer n = new Integer(10);
	private static final Integer n;

	static {
		System.out.println("static block DONE");
		m = 12;
		n = new Integer(10);
	}
	
	public WithPrivateFinalField() throws Exception {
		//Field f = WithPrivateFinalField.class.getDeclaredField("j");
		//f.setAccessible(true);
		//System.out.println("j=" + f.getInt(this));
		//this.s = "String S";
		//j = 111;
	}

	public WithPrivateFinalField(final String str) {
		//this.s = str;
		//j = 111;
	}

	public String toString() {
		// Field sbField;
		// Object obj = null;
		// try {
		// sbField = WithPrivateFinalField.class.getDeclaredField("sb");
		// //sbField.setAccessible(true);
		// obj = (Object) sbField.get(this);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return "i = " + i + "," + "  s= " + s + ",  s2= " + s2 + ",  sb = " + sb.toString() + ",  obj= " + obj + ",  j="
				+ j + ",  k=" + k + ",  m=" + m + ",  n=" + n + "is s3 == s: " + (s3 == s);
	}

}