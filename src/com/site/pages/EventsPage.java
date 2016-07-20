package com.site.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventsPage extends PageBase {

	// Constructor of DetailEventPage

	public EventsPage(WebDriver driver) {
		super(driver);
	}

	public void SelectLatestEvent() {
		WebElement Cell = driver
				.findElement(By
						.xpath("/html/body/div[8]/form/div[3]/table/tbody/tr/td[5]/a[1]"));
		Cell.click();
	}

}
