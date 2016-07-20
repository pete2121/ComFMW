package com.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {

	// Constructor of HomePage

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String Password) throws Exception {

		driver.findElement(By.id("username")).clear(); // Clear username text
														// area
		driver.findElement(By.id("password")).clear(); // Clear username text
														// password

		WebElement userName_editbox = driver.findElement(By.id("username"));
		WebElement password_editbox = driver.findElement(By.id("password"));
		WebElement submit_button = driver.findElement(By.name("submit"));

		userName_editbox.sendKeys(username);
		password_editbox.sendKeys(Password);
		submit_button.click();
		Thread.sleep(1000);

	}

	// public HomePage clickonRegistration() {
	// ClickButton("gb_70");
	// return new HomePage(driver);
	// }

}
