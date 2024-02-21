package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	
	public void setupDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getLogin() {
		WebElement loginUrl = driver.findElement(By.xpath("//a[contains(text(), 'Login/Signup')]"));
		
		loginUrl.click();
	}
	
	

}
