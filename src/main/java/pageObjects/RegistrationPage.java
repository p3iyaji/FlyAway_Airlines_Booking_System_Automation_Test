package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegistrationPage{
	
	WebDriver driver;
	
	public void setupDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getSignup() {
		
		WebElement loginSignupPage = driver.findElement(By.xpath("//a[contains(text(), 'Login/Signup')]"));
		loginSignupPage.click();
		
		WebElement signUpLink = driver.findElement(By.xpath("//a[contains(text(), 'Not a member? Signup')]"));
		signUpLink.click();
		
		WebElement email = driver.findElement(By.xpath("//input[@name='email_id']"));
		email.sendKeys("p43.iyaji@gmail.com");
		
		WebElement pwd = driver.findElement(By.xpath("//input[@name = 'pwd']"));
		pwd.sendKeys("admin123");
		WebElement pwd2 = driver.findElement(By.xpath("//input[@name = 'pwd2']"));
		pwd2.sendKeys("admin123");
		
		WebElement name = driver.findElement(By.xpath("//input[@name = 'name']"));
		name.sendKeys("Paul Iyaji");
		
		WebElement address = driver.findElement(By.xpath("//input[@name = 'address']"));
		address.sendKeys("19 Thirly");
		
		WebElement city = driver.findElement(By.xpath("//input[@name = 'city']"));
		city.sendKeys("Darlington");
		
		WebElement signupBtn = driver.findElement(By.xpath("//button[normalize-space()='Signup']"));
		signupBtn.click();
		
	}
	
	
	
	

}
