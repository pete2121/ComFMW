package com.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdateAuthorizationPage extends PageBase {

	// Constructor of HomePage

	public UpdateAuthorizationPage(WebDriver driver) {
		super(driver);
	}

	public void ClickLanguageButton() {
		WebElement button = driver.findElement(By.name("btn_language"));
		button.click();
	}

	public void ClickAddButton() {
		WebElement button = driver.findElement(By.name("btn_add"));
		button.click();
	}

	public void ClickValidateButton() {
		WebElement button = driver.findElement(By.name("btn_fill"));
		button.click();
	}

	public void ClickFacilitiesButton() {
		WebElement button = driver.findElement(By.name("btn_facilities"));
		button.click();
	}

	public void ClickAFFinishedButton() {
		WebElement button = driver.findElement(By.name("af_finish"));
		button.click();
	}

}
