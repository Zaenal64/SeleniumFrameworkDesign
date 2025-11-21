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

public class SubmitOrderTest extends BaseTest {
	
	@Test

	public void SubmitOrderTest() throws InterruptedException, IOException {
		String productName ="ZARA COAT 3";
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
	    Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Indonesia");
	    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	    
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Thread.sleep(3000);
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		System.out.println(confirmMessage);
		
		
		
		
		
		driver.quit();
	}

}
