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
public class AFeventsSign {

	static WebDriver driver;
	static HomePage HomePage;
	static LoginPage LoginPage;
	static TasksPage TasksPage;
	static EventsPage EventsPage;
	static DetailEventPage DetailEventPage;
	static UpdateAuthorizationPage UpdateAuthorizationPage;

	/**
	 * @throws Exception
	 */

	@BeforeClass
	public static void setUp() throws Exception {

		driver = new FirefoxDriver();
		Thread.sleep(10000);

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

		// HomePage.ChooseRole("Head of Unit"); Is Head of Unit by default

	}

	// Check If AF list is displayed, Check Label Existence
	@Test
	public void CheckAFListexistance() throws Exception {
		HomePage.VerifyallImagesofPage();
		HomePage.CheckElementExistance("afSmallListTable");
		HomePage.CheckLabelIndex("Events to AF sign", "caption", 1);

	}

	@Test
	public void ListRefreshed() {
		HomePage.ClickButton("afSmallListRefresh");
	}

	@Test
	public void OpenIconList() {
		HomePage.ClickButton("afSmallListExtend");
	}

	@Test
	public void ToActionAF() throws Exception {
		String context = HomePage.ReadEventID();
		Thread.sleep(10000);
		HomePage.SelecAFfromList(context);
		Thread.sleep(5000);
		HomePage.DecisionAFevent("sign", "This is test", false);
		Thread.sleep(15000);
		HomePage.ClickLink("Home");
		Thread.sleep(30000);
		HomePage.CheckApprovedAFColor(context);

	}

	@AfterClass
	public static void TearDown() throws Exception {
		driver.quit();
		Thread.sleep(8000);

	}

}
