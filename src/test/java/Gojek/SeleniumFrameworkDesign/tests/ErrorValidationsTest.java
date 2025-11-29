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

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws InterruptedException, IOException {
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		landingPage.loginApplication("anshika@gmail.com", "Imking");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		String validationError = landingPage.getErrorMessage();
		Assert.assertEquals(validationError, "Incorrect email or password.");
		System.out.println(validationError);
	}
	
	@Test
	public void SubmitOrderTest() throws InterruptedException, IOException {
		String productName ="ZARA COAT 3";
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("testjenal@mail.com", "@19oKtober");

		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
	    Assert.assertTrue(match);
	}
}
