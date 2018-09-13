package com.softserve.edu;

import java.lang.reflect.Constructor;

class A {
    int i = 11;
    //private int i = 11;
    private String s;

	public A() {
		//this.i = 111;
		System.out.println("\tConstructor public A()");
	}

    public A(int i) {
        this.i = i;
		System.out.println("\tConstructor public A(int i)");
    }

    public A(int i, String s) {
        this.i = i;
        this.s = s;
    }

    private int m1(int k) {
        System.out.println("run m1(), k = " + k + " i = " + i + " s= " + s);
        return k;
    }

    public int m2(String s, int k) {
        System.out.println("run m2(), s= " + s);
        m1(k);
        return i;
    }

    public void m2() {
        System.out.println("run m2()");
        m1(i);
    }

}

public final class Appl {
	public int m = 111;
	public Integer m2 = 112;
	
    public static void main(String[] args) throws Exception {
//        Class<?> c = LinkedList.class;
//        Class<?>[] interfaces = c.getInterfaces();
//        for (Class<?> cInterface : interfaces) {
//        	System.out.println(cInterface.getName());
//         }
        //
//		Class<?> c = Appl.class;
//		int mods = c.getModifiers();
//		System.out.println("mods = " + mods);
//		if (Modifier.isPublic(mods)) {
//			System.out.println("public");
//		}
//		if (Modifier.isAbstract(mods)) {
//			System.out.println("abstract");
//		}
//		if (Modifier.isFinal(mods)) {
//			System.out.println("final");
//		}
    	//
    	//Class<?> c = Appl.class;
//    	Class<?> c = A.class;
//    	Field[] publicFields = c.getFields();
    	//Field[] publicFields = c.getDeclaredFields();
//    	for (Field field : publicFields) {
//	    	Class<?> fieldType = field.getType();
//	    	System.out.println("Name: " + field.getName());
//	    	System.out.println("Type: " + fieldType.getName());
//    	}
    	//
//    	A a = new A();
//    	Class<?> c = a.getClass();
//    	Field field = c.getDeclaredField("i");
//    	int fieldMods = field.getModifiers();
//    	System.out.println("fieldMods = " + fieldMods);
//    	//Field field = c.getField("i");
//    	System.out.println("i = " + field.get(a));
//    	field.set(a, 321);
//    	a.m2();
    	//
//		A a = new A();
//		Class<?> c = a.getClass();
//		Constructor<?>[] constructors = c.getConstructors();
//		for (Constructor<?> constructor : constructors) {
//			System.out.print("constructor, parameters: ");
//			Class<?>[] paramTypes = constructor.getParameterTypes();
//			for (Class<?> paramType : paramTypes) {
//				System.out.print(paramType.getName() + "; ");
//			}
//			System.out.println();
//		}
    	//
        // get all metods
//		Class<?> c = Class.forName("com.softserve.edu.A");
//		Method[] methods = c.getDeclaredMethods();
//		for (Method method : methods) {
//			System.out.println("Name: " + method.getName() + "  mods = " + method.getModifiers());
//			System.out.println("Return Type: " + method.getReturnType().getName());
//			Class<?>[] paramTypes = method.getParameterTypes();
//			System.out.print("\tParameter Type: ");
//			for (Class<?> paramType : paramTypes) {
//				System.out.print("  " + paramType.getName());
//			}
//			System.out.println();
//		}
        //
//    	A a = new A(4132);
//    	Class<?> c = Class.forName("com.softserve.edu.A");
//    	Class<?>[] paramTypes = new Class[] { String.class, int.class };
//    	Method method = c.getMethod("m2", paramTypes);
//    	Object[] ar = new Object[] { new String("First Calculate"), new Integer(101) };
//    	int i = (int) method.invoke(a, ar);
//    	System.out.println("return i = " + i);
    	//
//    	A a = new A(4132);
//    	Class<?> c = Class.forName("com.softserve.edu.A");
//    	Class<?>[] paramTypes = new Class[] { int.class };
//    	Method method = c.getDeclaredMethod("m1", paramTypes);
//    	System.out.println("method m1, mods = " +  method.getModifiers());
//    	method.setAccessible(true);
//    	System.out.println("method m1, new mods = " +  method.getModifiers());
//    	Object[] ar = new Object[] { new Integer(123) };
//    	int k = (int) method.invoke(a, ar);
//    	System.out.println("return k = " + k);
    	//
        // A appl = new A();
        // Class<?> cappl = appl.getClass();
        // Class<?>[] paramTypes = new Class[] { int.class };
        // Method method = cappl.getDeclaredMethod("m1", paramTypes);
        // Object[] arr = new Object[] { new Integer (1010) };
        // method.setAccessible(true);
        // method.invoke(appl, arr);
        //
//		Class<?> c = Class.forName("com.softserve.edu.A");
//		Object obj = c.newInstance(); // Use Default Constructor
//		A a = (A) obj;
//		a.m2();
        //
		Class<?> c = Class.forName("com.softserve.edu.A");
		// Object obj = c.newInstance(); // Error, Default Constructor not found
		Class<?>[] paramTypes = new Class[] { int.class };
		Constructor<?> constructor = c.getConstructor(paramTypes);
		Object[] arr = new Object[] { new Integer(1011) };
		A a = (A) constructor.newInstance(arr);
		a.m2();
        //
    }
}
