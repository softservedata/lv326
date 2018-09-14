package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void testApp() throws Exception {
		//System.setProperty("webdriver.gecko.driver", applicationSource.getDriverPath());
        //new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",
				"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
                //"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://regres.herokuapp.com/");
		Thread.sleep(500);
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("work");
		Thread.sleep(500);
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("qwerty");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		Thread.sleep(500);
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='header']/div[2]/div/div/button"))
				.getText(), "work");
		driver.findElement(By.xpath("//div[@id='header']/div[2]/div/div/button[2]")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Вихід")).click();
		Thread.sleep(500);
		Assert.assertEquals(driver.findElement(By.linkText("Зареєструватися")).getText(), "Зареєструватися");
		driver.quit();
	}

}
