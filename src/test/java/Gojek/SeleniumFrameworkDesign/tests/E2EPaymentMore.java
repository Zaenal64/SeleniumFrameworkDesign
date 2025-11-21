package Gojek.SeleniumFrameworkDesign.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class E2EPaymentMore {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
		Map<String, Object> profile = new HashMap<String, Object>();
		profile.put("password_manager_leak_detection", false);
		prefs.put("profile", profile);
		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("anshika@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='enter your passsword']")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<String> targetProducts = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");

		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		// Loop through the target products and add them to the cart
		targetProducts.forEach(targetProduct -> {
			WebElement product = products.stream().filter(p -> 
			p.findElement(By.cssSelector("b")).getText().equals(targetProduct)).findFirst().orElse(null);
			if (product != null) {
				product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.cssSelector("div[class*='ng-animating']"))));
				wait.until(ExpectedConditions
						.invisibilityOf(driver.findElement(By.cssSelector("div[class*='ngx-spinner-overlay']"))));
			}
		});

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		JavascriptExecutor Js = (JavascriptExecutor)driver;
		Js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		Js.executeScript("window.scrollBy(0,-500)");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"indonesia").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		System.out.println(confirmMessage);

		driver.quit();
	}

}
