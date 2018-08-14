package com.softserve.edu;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

//@PrepareForTest({ Two.class, One.class })
@PrepareForTest({ Two.class })
public class TwoTest {
	private final double DELTA_PRECISION = 0.001;
	private One oneMock = PowerMockito.mock(One.class);
	private Two sutTwo;

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		System.out.println("\t\t+++class AppTest @BeforeClass");
		PowerMockito.whenNew(One.class).withNoArguments().thenReturn(oneMock);
		sutTwo = new Two();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t+++class AppTest @BeforeMethod");
		Mockito.reset(oneMock);
	}

	@DataProvider//(parallel = true)
    public Object[][] resumeData() {
        return new Object[][] {
                { 3, 3, 4 },
                { 5, 9, 10 }
        };
    }
	@Test(dataProvider = "resumeData")
	public void verifyResume1(double arg0, double result0, double expected) {
		System.out.println("\t\t\t\t+++class AppTest @Test verifyResume1()");
		//double arg0 = 3;
		//double result0 = 3;
		PowerMockito.when(oneMock.calc(arg0)).thenReturn(result0);
		//
		//double expected = 4;
		double actual = sutTwo.resume(arg0);
		System.out.println("actual= " + actual);
		Mockito.verify(oneMock).calc(arg0);
		Assert.assertEquals(actual, expected, DELTA_PRECISION, "My Error Message");
	}

}
