package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
    
    @BeforeTest
    public void beforeTest() {
        System.out.println("\t\t@BeforeTest - class AppTest.");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("\t\t@AfterTest - class AppTest");
    }

    @Test
    public void testApp1() {
        System.out.println("\t\t\t\t\t@Test testApp1()");
        Assert.assertTrue(true);
    }

    @Test
    public void testApp2() {
        System.out.println("\t\t\t\t\t@Test testApp2()");
        Assert.assertTrue(true);
    }

    @Test(enabled=false)
    public void divisionWithException( ) {
      System.out.println("Method is not ready yet");
    }

}
