package com.softserve.training;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//@PrepareForTest({ Two0.class, One0.class })
//@PrepareForTest({ Two0.class })
public class Two0Test {
	private final double DELTA_PRECISION = 0.001;
	private One0 oneMock = Mockito.mock(One0.class);
	//private One0 oneMock;
	private Two0 sutTwo;

	//@ObjectFactory
	//public IObjectFactory setObjectFactory() {
	//	return new PowerMockObjectFactory();
	//}

	@BeforeClass
	public void beforeClass() throws Exception {
		System.out.println("\t\t+++class AppTest @BeforeClass");
		//oneMock = Mockito.spy(new One0()); 
		sutTwo = new Two0(oneMock);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t+++class AppTest @BeforeMethod");
		//Mockito.reset(oneMock);
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
		Mockito.when(oneMock.calc(arg0)).thenReturn(result0);
		//
		//double expected = 4;
		double actual = sutTwo.resume(arg0);
		System.out.println("actual= " + actual);
		System.out.println("sutTwo.resume(10)= " + sutTwo.resume(10));
		Mockito.verify(oneMock).calc(arg0);
		Assert.assertEquals(actual, expected, DELTA_PRECISION, "My Error Message");
	}

}
