package com.flyawayairlines;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pageObjects.BookingConfirmation;
import pageObjects.LibDriver;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import pageObjects.SearchFlightPage;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

public class FlyAwayTest extends LibDriver {

	  String url = "http://localhost:8080/FlyAway/home";
	
	  RegistrationPage register = new RegistrationPage();
	  LoginPage loginurl = new LoginPage();
	  SearchFlightPage searchPage = new SearchFlightPage();
	  BookingConfirmation booking = new BookingConfirmation();
	  
	  
  @BeforeSuite
  public void setUp() {
	  this.driver = getDriver();
	  
  }
  
  @Test(priority = 0)
  public void verifyAppUrl() {
	  driver.get(url);
	  String expectedText = "Fly Away";
	  String actualText = driver.getTitle();
	  Assert.assertEquals(actualText, expectedText);
	  Reporter.log("App Url verified");
  }
  
  
  //Signing up and confirming registration
  @Test(priority = 1)
  public void verifyRegistrationPage() {
	  register.setupDriver(driver);
	  register.getSignup();
	  try {
		  Thread.sleep(2000);
		  
		  String confirmationPageTitle = driver.getTitle();
		  String api = driver.getCurrentUrl();
		  Response response = RestAssured.get(api);
		  System.out.println("Registration was successful with status code: "+response.statusCode());
		  System.out.println(api);
		  
		  String expectedTitle = "Fly Away - Registration Confirmation";
		  Assert.assertEquals(confirmationPageTitle, expectedTitle);
	  }catch(Exception e) {
		  e.printStackTrace();
	  } 
	  Reporter.log("Registration page verified");
  }
  
  
 //Using Data to login and confirming login
  @Test(dataProvider = "CredentialSupplier", priority = 2)
  public void verifyLogin(String username, String password) {
	  loginurl.setupDriver(driver);
	  loginurl.getLogin();
	  
	  WebElement email = driver.findElement(By.xpath("//input[@name = 'email_id']"));
	  WebElement pwd = driver.findElement(By.xpath("//input[@name = 'pwd']"));
	  
	  email.sendKeys(username);
	  pwd.sendKeys(password);
	  
	  WebElement loginBtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
	  loginBtn.click();
	  
	  WebElement verifyLogin = driver.findElement(By.xpath("//h3[normalize-space()='FLYAWAY - DASHBOARD']"));
	  Assert.assertTrue(verifyLogin.isDisplayed());
	  
	  Reporter.log("New user registration verified");
	  
  }
  
  
  //Confirming Search Flight page and searching for flight
  @Test(priority = 3)
  public void verifySearchFlightPage() {
	  searchPage.setupDriver(driver);
	  searchPage.getSearchPage();
	  try {
			Thread.sleep(2000);
			WebElement verifySearchPage = driver.findElement(By.xpath("//h3[normalize-space()='FLYAWAY HOMEPAGE']"));
			Assert.assertTrue(verifySearchPage.isDisplayed());	
		}catch(Exception e) {
			e.printStackTrace();
		}
	  
	  Reporter.log("Search Flight page verified");
  }
  
  @Test(priority = 4)
  public void verifySearchFlight() {
	  searchPage.searchFlights();
	  WebElement verifyBookFlight = driver.findElement(By.xpath("//a[normalize-space()='Book Flight']"));
	  verifyBookFlight.click();
	  
	  Reporter.log("Booking flight using source and destination verified");
  }
  
  @Test(priority = 5)
  public void verifyPaymentGateway() {
	  booking.setupDriver(driver);
	  Assert.assertTrue(booking.paymentGateway().isDisplayed());
	  
	  Reporter.log("Payment gateway verified");
	  
  }
  
  @Test(priority = 6)
  public void verifyBookingConfirmation() {
	  booking.completeBooking();
	  try {
		  Thread.sleep(2000);
		  WebElement confirmation = driver.findElement(By.xpath("//a[contains(text(), 'FLYAWAY - BOOKING CONFIRMATION')]"));
		  Assert.assertTrue(confirmation.isDisplayed());
	  }catch(Exception e) {
		 e.printStackTrace();
	  }
	  
	  Reporter.log("Booking confirmed");
  }
  
  @Test(priority = 7)
  public void testLoginApi() {
	  String api = "http://localhost:8080/FlyAway/login";
	  Response response = RestAssured.get(api);
	  System.out.println(response.statusCode());
	  System.out.println("RESPONSE: Login Page");
	  System.out.println(response.prettyPrint());
	  int status_code = response.statusCode();
	  int expectedStatus_code = 200;
	  Assert.assertEquals(status_code, expectedStatus_code);
	  
	  Reporter.log("Login page endpoint verified");
	  
  }
  
  @Test(priority = 8)
  public void testSearchFlightApi() {
	  String api = "http://localhost:8080/FlyAway/home?source=1&destination=4";
	  Response response = RestAssured.get(api);
	  System.out.println(response.statusCode());
	  System.out.println("RESPONSE: Search Flight Page");
	  System.out.println(response.prettyPrint());
	  int status_code = response.statusCode();
	  int expectedStatus_code = 200;
	  Assert.assertEquals(status_code, expectedStatus_code);
	  
	  Reporter.log("Search Flight page endpoint verified");
  }
  
  
  @AfterSuite
  public void afterSuite() {
	  driver.quit();
	  Reporter.log("Application closed");
  }
  
  
  
  //Reading data from the excel sheet
  @DataProvider(name="CredentialSupplier")
	public Object[][] dataSupplier() throws IOException {
		
		//getting the excel file
		File excelFile = new File(System.getProperty("user.dir")+ "//TestData.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);  
		XSSFSheet sheet = workbook.getSheet("login");  //getting the required sheet name
			
			//getting the rows and columns
			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rowCount][columnCount];
			
			for(int r=0; r<rowCount; r++) {
				XSSFRow row = sheet.getRow(r + 1);
				
				for(int c=0; c<columnCount; c++) {
					XSSFCell cell = row.getCell(c);
					CellType cellType = cell.getCellType();
					
					switch(cellType) {
					
					case STRING:
						data[r][c] = cell.getStringCellValue();
						break;
						
					case NUMERIC:
						data[r][c] = Integer.toString((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
				}
				
			}
			workbook.close();
			
			return data;
		}
		
	

}
