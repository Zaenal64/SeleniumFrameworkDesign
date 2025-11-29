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
import PageObjects.OrderPage;
import PageObjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest {
	String productName ="ZARA COAT 3";
	
	@Test
	public void SubmitOrderTest() throws InterruptedException, IOException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("testjenal@mail.com", "@19oKtober");

		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
	    Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Indonesia");
	    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Thread.sleep(3000);
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		System.out.println(confirmMessage);
		

	}
	
	@Test
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("testjenal@mail.com", "@19oKtober");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
    }

}
