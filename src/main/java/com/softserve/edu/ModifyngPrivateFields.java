package com.softserve.edu;

import java.lang.reflect.Field;

import com.softserve.edu.Hahaha;

public class ModifyngPrivateFields {
	
	public static void main(String[] args) throws Exception {
		// Hahaha h = new Hahaha();
		WithPrivateFinalField pf = new WithPrivateFinalField();
		System.out.println("ORIGIN:");
		System.out.println(pf);
		Field f;
		//
		System.out.println("MODIFY:");
		//
//		 f = pf.getClass().getDeclaredField("i");
//		 f.setAccessible(true);
//		 f.setInt(pf, 47);
//		 System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("s");
//         f.setAccessible(true);
//         //f.set(pf, "MODIFY S");
//         f.set(pf, new String("MODIFY S"));
//         System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("s2");
//         f.setAccessible(true);
//         f.set(pf, "MODIFY S2");
//         System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("sb");
//         f.setAccessible(true);
//         StringBuilder sb = (StringBuilder) f.get(pf);
//         System.out.println("StringBuilder sb = " + (Object) sb);
//         //f.setAccessible(true);
//         f.set(pf, new StringBuilder("MODIFY SB"));
//         System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("obj");
//         f.setAccessible(true);
//         f.set(pf, new Object());
//         System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("j");
//         f.setAccessible(true);
//         // f.setInt(pf, 222);
//         f.set(pf, 222);
//         System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("k");
//         f.setAccessible(true);
//         f.set(pf, 20);
//         //f.set(pf, new Integer(202));
//         System.out.println(pf);
		//
//         f = pf.getClass().getDeclaredField("m");
//         f.setAccessible(true);
//         f.setInt(null, 222); // Ok for not final
//         //f.setInt(pf, 222); // Ok for not final
//         // f.set(pf, 130); // ERROR
//         System.out.println("m= " + f.getInt(pf) + "\n" + pf);
		//
//		f = pf.getClass().getDeclaredField("n");
//		f.setAccessible(true);
//		f.setInt(pf, 222);
//		//f.set(pf, new Integer(130)); // ERROR
//		System.out.println("n= " + (int)f.get(pf) + "\n" + pf);
		//
	}
}
