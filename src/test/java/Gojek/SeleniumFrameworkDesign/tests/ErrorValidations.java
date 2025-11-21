package Gojek.SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Gojek.TestComponents.BaseTest;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	@Test

	public void SubmitOrderTest() throws InterruptedException, IOException {
		String productName ="ZARA COAT 3";
		landingPage.loginApplication("anshika@gmail.com", "Imking");
		Assert.assertEquals("Incorrect email or pass@ord.", landingPage.getErrorMessage());
	
		
		
		driver.quit();
	}

}
