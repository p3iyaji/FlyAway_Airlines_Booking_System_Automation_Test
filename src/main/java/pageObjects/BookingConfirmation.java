package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingConfirmation {
	
	WebDriver driver;
	
	public void setupDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement paymentGateway() {
		WebElement pageText = driver.findElement(By.xpath("//h3[normalize-space()='FLYAWAY - PAYMENT GATEWAY']"));
		return pageText;
	}
	
	public void completeBooking() {
		WebElement bookingComplete = driver.findElement(By.xpath("//a[contains(text(), 'Click to complete booking')]"));
		bookingComplete.click();
	}
}
