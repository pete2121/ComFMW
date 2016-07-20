package com.site.pages;

//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailEventPage extends PageBase {

	// Constructor of DetailEventPage

	public DetailEventPage(WebDriver driver) {
		super(driver);
	}

	public void ClickSaveButton() {
		WebElement button = driver.findElement(By.name("dispatch"));
		button.click();
	}

	public void ClickCancelButton() {
		WebElement button = driver.findElement(By.name("cancel"));
		button.click();

	}

	public void ClickModifyButton() {
		WebElement button = driver.findElement(By.name("update"));
		button.click();

	}

	public void ClickSubjectButton() {
		WebElement button = driver.findElement(By.name("btn_subject"));
		button.click();
	}

	public void ClickLegislationButton() {
		WebElement button = driver.findElement(By.name("btn_legislation"));
		button.click();
	}

	public void ClickAutorizationFormButton() {
		WebElement button = driver.findElement(By.name("btn_autho"));
		button.click();
	}

	public void ClicklegislationFormButton() {
		WebElement button = driver.findElement(By.name("btn_legislation"));
		button.click();
	}

	public void ClickApproveAFButton() {
		WebElement button = driver.findElement(By.name("af_tl_a"));
		button.click();
	}

	public void SetEULegislation(int NumberofList) throws Exception {
		Thread.sleep(4000);
		driver.switchTo().frame("treeview");
		WebElement CheckBox = driver.findElement(By
				.xpath("/html/body/form/div/div/table[" + NumberofList
						+ "]/tbody/tr/td[2]/input"));
		Thread.sleep(5000);
		CheckBox.click();
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
	}

	public void GetEventID() throws Exception {

		WebElement Element = driver
				.findElement(By.name("selectedBean.eventID"));
		String id = Element.getAttribute("value");
		PrintWriter out = new PrintWriter("H:\\EventID.txt");
		out.println(id);
		out.close();
	}
	
	public String ReadEventID1() throws IOException {

		String content = new String(Files.readAllBytes(Paths
				.get("H:\\EventID.txt")));
		return content;

	}

	public void CheckLatestEventIDinDB() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		String url1 = "jdbc:oracle:thin:@ORAEXT2.CC.CEC.EU.INT:1526:EX1UELGB";
		String dbClass = "oracle.jdbc.driver.OracleDriver";
		Class.forName(dbClass).newInstance();
		Connection con = DriverManager.getConnection(url1, "jcstaiex",
				"kjdx#n6gf5d");
		Statement stmt = (Statement) con.createStatement();
		ResultSet result = (ResultSet) stmt
				.executeQuery("select * from  TD_EVENT where EV_PK_IDEVENT in (select max(EV_PK_IDEVENT) from TD_EVENT)");
		while (result.next()) {
			String Id = result.getString("EV_PK_IDEVENT");
			System.out
					.println("Event Id that can be retreived from TD_EVENT table is:"
							+ Id);
			System.out
					.println("Comparing the Event ID into Database with the Event ID in UI");

			String context = ReadEventID1();
			Thread.sleep(10000);
			Assert.assertEquals(Id.trim(), context.trim());

		}
		result.close();
		con.close();
	}

}
