/*
 * (C) Copyright 2021 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.iways.selenium.test;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.iway.selenium.test.SpringBootDemoApp;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test using a local web application based on spring-boot.
 *
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringChromeTest {

	private WebDriver driver;
	JavascriptExecutor js;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		new HashMap<String, Object>();
	}

	@After
	public void teardown() {
		if (driver != null) {
			// driver.quit();//
		}
	}

	@Test
	public void test() throws InterruptedException {
		// Open system under test

		driver.get("https://testwww.gebana.de/shop/eu_en/customer/account/login/");
		driver.manage().window().maximize();
		WebElement cookiesBox = driver.findElement(By.id("notice-cookie-block"));
		WebElement cookiesBT = cookiesBox.findElement(By.id("btn-cookie-allow"));
		System.out.println("cookieBT" + cookiesBox);
		if (cookiesBT != null) {
			// cookiesBT.click();
		}
		// driver.manage().window().setSize(new Dimension(607, 559));
		WebElement emailElement = driver.findElement(By.id("email"));
		Actions emailBuilder = new Actions(driver);
		emailBuilder.doubleClick(emailElement).perform();
		emailElement.sendKeys("testing-gebana@i-ways.net");
		// driver.findElement(By.id("pass")).click();
		WebElement passwordElement = driver.findElement(By.id("pass"));
		Actions passswordBuilder = new Actions(driver);
		passswordBuilder.doubleClick(passwordElement).perform();
		driver.findElement(By.id("pass")).sendKeys("Test@Gebana123");
		js.executeScript("window.scrollTo(0,222.83465576171875)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id=\'send2\']/span")).click();
		driver.get("https://testwww.gebana.de/shop/eu_en/checkout/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"iosc_billingaddress\"]/label/span")).click();
		Thread.sleep(2000);
		WebElement radio2 = driver.findElement(By.xpath("//div[3]/div/label"));
		radio2.click(); // System.out.println("radio.isselected");
		Thread.sleep(3000);
		driver.switchTo().frame("fields-card-number");
		driver.findElement(By.xpath("//*[@id=\"fields-card-number\"]/input")).sendKeys("9010 0031 5000 0001");
		driver.switchTo().parentFrame();
		driver.switchTo().frame("fields-holder-name");
		driver.findElement(By.xpath("//*[@id=\"fields-holder-name\"]/input")).sendKeys("GebanaTest");
		driver.switchTo().parentFrame();
		driver.switchTo().frame("fields-expiration");
		driver.findElement(By.xpath("//*[@id=\"fields-expiration\"]/input")).sendKeys("12/11");
		driver.switchTo().parentFrame();
		driver.switchTo().frame("fields-cvc");
		driver.findElement(By.xpath("//*[@id=\"fields-cvc\"]/input")).sendKeys("121");
        WebElement buyButton = driver.findElement(By.cssSelector("#iosc-summary > div.iosc-place-order-container > button"));
        buyButton.click();
		//driver.findElement(By.xpath("//*[@id=\"iosc-comment-heading\"]")).click();
		//WebElement buyButton = driver.findElement(By.xpath("//*[@id=\"VQLQ5RR\"]"));
		//buyButton.click();//*[@id="saferpay-field-checkout"]
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"saferpay-field-checkout\"]")).click();
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
		///submitBtn.click();
		
	}
}
