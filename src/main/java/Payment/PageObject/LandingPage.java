package Payment.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(xpath="//input[@type='email']")
	WebElement userEmail;
	
	@FindBy(css="input[placeholder='enter your passsword']")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;

}
