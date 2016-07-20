package com.site.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

//import com.publicsite.pages.PageBase;
import com.site.pages.LoginPage;
import com.site.pages.HomePage;
import com.site.pages.TasksPage;
import com.site.pages.EventsPage;
import com.site.pages.DetailEventPage;
import com.site.pages.UpdateAuthorizationPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApproveAF {

	static WebDriver driver;
	static HomePage HomePage;
	static LoginPage LoginPage;
	static TasksPage TasksPage;
	static EventsPage EventsPage;
	static DetailEventPage DetailEventPage;
	static UpdateAuthorizationPage UpdateAuthorizationPage;

	@BeforeClass
	public static void setUp() throws Exception {

		driver = new FirefoxDriver();
		Thread.sleep(10000);
		driver.manage().window().maximize();

		String baseUrl;
		baseUrl = "https://webgate.ec.europa.eu/cas/login";
		driver.navigate().to(baseUrl);
		HomePage = new HomePage(driver);
		LoginPage = new LoginPage(driver);
		TasksPage = new TasksPage(driver);
		DetailEventPage = new DetailEventPage(driver);
		EventsPage = new EventsPage(driver);
		UpdateAuthorizationPage = new UpdateAuthorizationPage(driver);
	}

	@Test
	public void ApplicationLogIn() throws Exception {

		driver.findElement(By.cssSelector("span.name")).click(); // Just Click
																	// on Start
																	// at
																	// European
																	// Comission
		Thread.sleep(2000);
		System.out.println("Login Into the application");
		Thread.sleep(4000);
		LoginPage.login("plakofo", "xxx"); //ADD YOUR PASSWORD
		driver.navigate().to("https://webgate.acceptance.ec.europa.eu/TMS");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		HomePage.ChooseRole("Team Leader");
		Thread.sleep(2000);
		HomePage.ClickLink("Events");
		EventsPage.SelectLatestEvent();
		Thread.sleep(2000);

	}

	@Test
	public void ApproveAuthorizationForm() throws Exception {
		System.out.println("Approve Authorization Form");
		DetailEventPage.ClickApproveAFButton();
		DetailEventPage.SwitchToNewWindow("Worflow_Comment");
		DetailEventPage.TypebyText("comment", "This is test by Petros");
		DetailEventPage.ClickButton("btn_save");

	}

	@Test
	public void VerifythatAFisapproved() throws Exception {
		DetailEventPage.handleMultipleWindows("TMS - Detail of an event");
		Thread.sleep(2000);
		DetailEventPage.ClickButton("btn_refresh");
		Thread.sleep(6000);
		DetailEventPage.VerifyTextPresentsAtPage("AF TL Approved.");
		DetailEventPage.CheckMessageByIdContains("AF TL Approved.",
				"calculationSheet");
		Thread.sleep(5000);
	}

	@AfterClass
	public static void TearDown() throws Exception {
		driver.quit();
		Thread.sleep(8000);

	}

}
