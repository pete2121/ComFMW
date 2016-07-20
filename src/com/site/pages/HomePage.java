package com.site.pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

//import org.junit.Test;

public class HomePage extends PageBase {

	// Constructor of HomePage

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void ChooseRole(String Role) throws InterruptedException {
		driver.findElement(By.xpath("//button[@type='button']")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText(Role)).click();
		Thread.sleep(4000);
		ClickButtonbyText("Yes");
		Thread.sleep(4000);

	}

	public void DecisionAFevent(String Decision, String Comment, Boolean Cancel)
			throws InterruptedException {

		if (Decision == "Sign" || Decision == "sign") {
			ClickButton("afSignBtn");
			Thread.sleep(3000);

			WebElement editbox = driver
					.findElement(By.className("commentText"));
			editbox.sendKeys(Comment);

			if (Cancel == true) {

				WebElement button = driver
						.findElement(By
								.xpath("/html/body/div[7]/div[2]/div/div[6]/div/div/div[3]/button[2]"));
				button.click();

				return; // Exit method

			}

			else if (Cancel == false) {

				WebElement button = driver
						.findElement(By
								.xpath("/html/body/div[7]/div[2]/div/div[6]/div/div/div[3]/button[1]"));
				button.click();
				Thread.sleep(4000);
				//Close button
				WebElement Closebutton = driver
						.findElement(By
								.xpath("/html/body/div[7]/div[2]/div/div[6]/div/div/div[3]/button[2]"));
				Closebutton.click();

			}

		}

		else if (Decision == "Refuse" || Decision == "refuse") {
			ClickButton("afRefuseBtn");

			Thread.sleep(3000);

			WebElement editbox = driver
					.findElement(By.className("commentText"));
			editbox.sendKeys(Comment);

			if (Cancel == true) {

				WebElement button = driver
						.findElement(By
								.xpath("/html/body/div[7]/div[2]/div/div[6]/div/div/div[3]/button[2]"));
				button.click();

				return; // Exit method

			}

			else if (Cancel == false) {

				WebElement button = driver
						.findElement(By
								.xpath("/html/body/div[7]/div[2]/div/div[6]/div/div/div[3]/button[1]"));
				button.click();
				Thread.sleep(4000);
				//Close button
				WebElement Closebutton = driver
						.findElement(By
								.xpath("/html/body/div[7]/div[2]/div/div[6]/div/div/div[3]/button[2]"));
				Closebutton.click();


			}

		}
	}

	// There is a bug for id:afDetailedListTable, developers should fixed this!
	public void SelecAFfromList(String Eventid) {
		System.out.println("Event Id is the:" + Eventid);
		WebElement table = driver.findElement(By.id("afSmallListTable"));
		List<WebElement> tr_collection = table.findElements(By.tagName("tr"));
		int iSize = tr_collection.size();
		// Start the loop from the first row to the last row
		for (int i = 0; i < iSize + 1; i++) {
			String sValue = tr_collection.get(i).getText();
			if ((sValue.trim()).contains(Eventid.trim())) {
				tr_collection.get(i).click();
				break;
			}
		}
	}

	public void CheckApprovedAFColor(String Eventid) throws Exception {

		List<WebElement> ApprovedElements=driver.findElements(By.cssSelector("td.approved>strong>span.eventID"));
		
		int iSize = ApprovedElements.size();
		// Start the loop from the first row to the last row
		for (int i = 0; i < iSize + 1; i++) {
			String sValue = ApprovedElements.get(i).getText();
			if ((sValue.trim()).contains(Eventid.trim())) {
				Assert.assertEquals(sValue.trim(), Eventid.trim());
				System.out.println("The AF is into approved list" + Eventid);
				break;
			}
		}
	   
	  Thread.sleep(6000);
	  //Check that the approved list has the appropriate color
	  WebElement element = driver.findElement(By.cssSelector("td.approved"));
	  String ActualColor = element.getCssValue("background-color");

		// Convert RGBA to HEX Format
		String[] numbers = ActualColor.replace("rgba(", "").replace(")", "")
				.split(",");
		int r = Integer.parseInt(numbers[0].trim());
		int g = Integer.parseInt(numbers[1].trim());
		int b = Integer.parseInt(numbers[2].trim());
		// System.out.println("r: " + r + " g: " + g + " b: " + b);

		String hexActual = "#" + Integer.toHexString(r)
				+ Integer.toHexString(g) + Integer.toHexString(b);
		// System.out.println(hex);
		//

		Assert.assertEquals("#b5e3b2", hexActual);

	}

	public void CheckRefusedAFColor(String Eventid) throws Exception {
		
List<WebElement> ApprovedElements=driver.findElements(By.cssSelector("td.refused>strong>span.eventID"));
		
		int iSize = ApprovedElements.size();
		// Start the loop from the first row to the last row
		for (int i = 0; i < iSize + 1; i++) {
			String sValue = ApprovedElements.get(i).getText();
			if ((sValue.trim()).contains(Eventid.trim())) {
				Assert.assertEquals(sValue.trim(), Eventid.trim());
				System.out.println("The AF is into refused list" + Eventid);
				break;
			}
		}
	
	  Thread.sleep(6000);
	  //Check that the refused list has the appropriate color
		WebElement element = driver.findElement(By.cssSelector("td.refused"));
		String ActualColor = element.getCssValue("background-color");

		// Convert RGBA to HEX Format
		String[] numbers = ActualColor.replace("rgba(", "").replace(")", "")
				.split(",");
		int r = Integer.parseInt(numbers[0].trim());
		int g = Integer.parseInt(numbers[1].trim());
		int b = Integer.parseInt(numbers[2].trim());
		// System.out.println("r: " + r + " g: " + g + " b: " + b);

		String hexActual = "#" + Integer.toHexString(r)
				+ Integer.toHexString(g) + Integer.toHexString(b);
		// System.out.println(hex);
		//

		Assert.assertEquals("#f09696", hexActual);
	}


	public String ReadEventID() throws IOException {

		String content = new String(Files.readAllBytes(Paths
				.get("H:\\EventID.txt")));
		return content;

	}

    //Example For Navigation to go at TasksPage
	//public TasksPage ClickonTasks() throws Exception {
	//	ClickLink("Tasks");
		//Thread.sleep(1000);
       // return new TasksPage(driver);
	//}
	
	
}
