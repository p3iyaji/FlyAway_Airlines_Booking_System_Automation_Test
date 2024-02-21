package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchFlightPage {
	
	WebDriver driver;
	
	public void setupDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getSearchPage() {
		WebElement searchPage = driver.findElement(By.xpath("//a[contains(text(), 'Home')]"));
		searchPage.click();
		
	}

	public void searchFlights() {
		Select source = new Select(driver.findElement(By.name("source")));
		source.selectByVisibleText("Bangalore");
		
		Select destination = new Select(driver.findElement(By.name("destination")));
		destination.selectByVisibleText("Chennai");
		
		WebElement submitBtn = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
		submitBtn.click();
		
		
	}
	
}
