package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegistrationPage{
	
	WebDriver driver;
	public String email_id = "p60.iyaji@gmail.com";
	public String password = "admin123";
	public String password2 = "admin123";
	public String fullname = "Paul Iyaji";
	public String fulladdress = "19 Thirly";
	public String city_id = "Darlington";
	
	public void setupDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getSignup() {
		
		WebElement loginSignupPage = driver.findElement(By.xpath("//a[contains(text(), 'Login/Signup')]"));
		loginSignupPage.click();
		
		WebElement signUpLink = driver.findElement(By.xpath("//a[contains(text(), 'Not a member? Signup')]"));
		signUpLink.click();
		
		WebElement email = driver.findElement(By.xpath("//input[@name='email_id']"));
		email.sendKeys(email_id);
		
		WebElement pwd = driver.findElement(By.xpath("//input[@name = 'pwd']"));
		pwd.sendKeys(password);
		WebElement pwd2 = driver.findElement(By.xpath("//input[@name = 'pwd2']"));
		pwd2.sendKeys(password2);
		
		WebElement name = driver.findElement(By.xpath("//input[@name = 'name']"));
		name.sendKeys(fullname);
		
		WebElement address = driver.findElement(By.xpath("//input[@name = 'address']"));
		address.sendKeys(fulladdress);
		
		WebElement city = driver.findElement(By.xpath("//input[@name = 'city']"));
		city.sendKeys(city_id);
		
		WebElement signupBtn = driver.findElement(By.xpath("//button[normalize-space()='Signup']"));
		signupBtn.click();
		
	}
	
	
	
	

}
