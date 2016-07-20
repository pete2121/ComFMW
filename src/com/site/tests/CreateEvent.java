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
public class CreateEvent {

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
		// driver.manage().window().maximize();

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
		HomePage.ChooseRole("Super User");
		Thread.sleep(3000);
		DetailEventPage.CheckLatestEventIDinDB();

	}

	@Test
	public void ClickonTasks() throws Exception {
		System.out.println("Click on Tasks");
		HomePage.ClickLink("Tasks");
		Thread.sleep(1000);

	}

	@Test
	public void SelectTasks() throws Exception {
		System.out.println("Select a Tasks");
		Thread.sleep(10000);
		TasksPage.buttonrows();

	}

	@Test
	public void TaskSelectDecision() throws Exception {
		System.out.println("Select Decision");
		Thread.sleep(10000);
		TasksPage.selectfromDropDownList("takenDecisionSelect", "Yes", null);
		TasksPage.ClickModalButtonSave();
		Thread.sleep(2000);
		TasksPage.CheckLabel(
				"The Approval Form is mandatory in case of YES / NO",
				"errorArea");
		Thread.sleep(2000);
		TasksPage
				.UploadFile("C:\\Users\\plakofo\\Desktop\\Packt.Publishing.Apache.JMeter.Jun.2008.pdf");
		TasksPage.ClickModalButtonSave();
		Thread.sleep(10000);
		DetailEventPage.Type("place", "Athens");
		DetailEventPage.SetEULegislation(1);
		Thread.sleep(5000);
		DetailEventPage.ClickSaveButton();
		Thread.sleep(5000);
		System.out.println("Event has been created");
		TasksPage.CheckMessage("New event has been added with success.",
				"message_SUCCESS");
	}

	@Test
	public void ViewModifyTaskDetailEvent() throws Exception {
		// Change Role
		HomePage.ChooseRole("IBU Case Handler");
		Thread.sleep(2000);
		System.out.println("Into task detail Event");
		Thread.sleep(10000);
		HomePage.ClickLink("Events");
		EventsPage.SelectLatestEvent();
		Thread.sleep(2000);
		DetailEventPage.GetEventID();
		DetailEventPage.ClickModifyButton();
		Thread.sleep(2000);
		DetailEventPage.Type("startDate", "01/01/2016");
		DetailEventPage.Type("endDate", "01/01/2017");
		DetailEventPage.Type("place", "Buenos");
		DetailEventPage.Type("eventKeyword", "AEK");
		DetailEventPage.selectfromDropDownListbyName("countryId", null, "163");
		DetailEventPage.selectfromDropDownListbyName("caseHandlerID", null,
				"135");
		DetailEventPage.ClickSaveButton();
		DetailEventPage.ClickAutorizationFormButton();
		UpdateAuthorizationPage.SwitchToNewWindow("detail");
		Thread.sleep(4000);
		UpdateAuthorizationPage.Type("selectedBean.proposedAction",
				"This is Test");
		UpdateAuthorizationPage.Type("selectedBean.exactDefinition",
				"This is Test");
		UpdateAuthorizationPage.Type("selectedBean.exactDefinition",
				"This is Test");
		UpdateAuthorizationPage.Type("selectedBean.beneficiaryInst",
				"This is Test");
		UpdateAuthorizationPage.Type("selectedBean.coOrganisation", "Petros");
		UpdateAuthorizationPage.Type("selectedBean.backgroundInfo", "Petros2");
		UpdateAuthorizationPage.SelectRadioButton("selectedBean.afInfoID", "1");
		UpdateAuthorizationPage.Type("selectedBean.afInfoComment",
				"This is a comment");
		// UpdateAuthorizationPage.Type("selectedBean.nbrExpert", "1");
		// UpdateAuthorizationPage.Type("bestimat[0].participant", "10");
		// UpdateAuthorizationPage.Type("bestimat[2].participant", "5");
		UpdateAuthorizationPage.ClickLanguageButton();
		UpdateAuthorizationPage.SwitchToNewWindow("languages");
		Thread.sleep(4000);
		UpdateAuthorizationPage.selectfromDropDownList("toLanguageID", null,
				"Estonian (ET)");
		UpdateAuthorizationPage.ClickAddButton();
		UpdateAuthorizationPage.ClickValidateButton();
		UpdateAuthorizationPage.SwitchToNewWindow("detail");
		Thread.sleep(4000);
		UpdateAuthorizationPage.ClickFacilitiesButton();
		UpdateAuthorizationPage.SwitchToNewWindow("conference_facilities");
		Thread.sleep(4000);
		UpdateAuthorizationPage.TypebyText("participant", "10");
		UpdateAuthorizationPage.TypebyText("speaker", "1");
		UpdateAuthorizationPage.TypebyText("Wireless micro", "2");
		UpdateAuthorizationPage.TypebyText("Head phones", "2");
		UpdateAuthorizationPage.SelectCheckBox("technical", "LCD");
		UpdateAuthorizationPage.ClickValidateButton();
		UpdateAuthorizationPage.SwitchToNewWindow("detail");
		Thread.sleep(4000);
		UpdateAuthorizationPage.ClickSaveButton();
		UpdateAuthorizationPage.CheckMessage(
				"The Authorisation Form has been modified with success.",
				"message_SUCCESS");
		UpdateAuthorizationPage.CloseWindow();
		Thread.sleep(5000);

	}
	

	@AfterClass
	public static void TearDown() throws Exception {
		driver.quit();
		Thread.sleep(8000);

	}

}
