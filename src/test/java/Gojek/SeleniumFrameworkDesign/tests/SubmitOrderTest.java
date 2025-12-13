package Gojek.SeleniumFrameworkDesign.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Gojek.TestComponents.BaseTest;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.OrderPage;
import PageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input)
			throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
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
	public void OrderHistoryTest() {
		// "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("testjenal@mail.com", "@19oKtober");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Gojek//data//PurchaseOrder.json");
		return new Object[][] {
				{data.get(0)}, {data.get(1)} 
		};
	}
	
//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//	    
//	  }
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "shetty@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ADIDAS ORIGINAL");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "testjenal@mail.com");
//	map1.put("password", "@19oKtober");
//	map1.put("product", "ZARA COAT 3");
	
	
	

}
