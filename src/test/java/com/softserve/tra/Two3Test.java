package com.softserve.tra;

//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ Two3.class, One3.class })
//@PrepareForTest({ Two3.class })
@PrepareForTest({ One3.class })
public class Two3Test { //extends PowerMockTestCase {
	private final double DELTA_PRECISION = 0.001;
	//private One3 oneMock = PowerMockito.mock(One3.class);
	private One3 oneMock;
	private Two3 sutTwo;

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		System.out.println("\t\t+++class AppTest @BeforeClass");
		//PowerMockito.whenNew(One3.class).withNoArguments().thenReturn(oneMock);
		oneMock = PowerMockito.mock(One3.class);
		sutTwo = new Two3(oneMock);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t+++class AppTest @BeforeMethod");
		//sutTwo = new Two3(oneMock);
		Mockito.reset(oneMock);
	}

	@DataProvider // (parallel = true)
	public Object[][] resumeData() {
		return new Object[][] {
			{ 3, 3, 4 },
			{ 5, 9, 10 }
		};
	}

	@Test(dataProvider = "resumeData")
	public void verifyResume1(double arg0, double result0, double expected) {
	//public void verifyResume1() {
		System.out.println("\t\t\t\t+++class AppTest @Test verifyResume1()");
		//double arg0 = 3;
		//double result0 = 3;
		PowerMockito.when(oneMock.calc(arg0)).thenReturn(result0);
		//
		//double expected = 4;
		double actual = sutTwo.resume(arg0);
		System.out.println("actual= " + actual);
		Mockito.verify(oneMock).calc(arg0);
		//Assert.assertEquals(actual, expected, DELTA_PRECISION, "My Error Message");
	}

}
