package com.site.pages;

import org.openqa.selenium.WebDriver;

public class TasksPage extends PageBase {

	// Constructor of HomePage

	public TasksPage(WebDriver driver) {
		super(driver);
	}

	public void CreationofTask(String requester, String evalCorrespond,
			String letterDate, String eventType, String clientLeader,
			String projectID, String beneficiary) throws InterruptedException {

		System.out.println("Add a Task");
		Thread.sleep(3000);
		ClickButton("btn_insert");
		Thread.sleep(1000);
		Type("selectedBean.requester", requester);
		Type("selectedBean.evalCorrespond", evalCorrespond);
		Type("selectedBean.letterDate", letterDate);
		selectfromDropDownListbyName("selectedBean.eventType", null, eventType);
		selectfromDropDownListbyName("selectedBean.clientLeader", null,
				clientLeader);
		selectfromDropDownListbyName("selectedBean.projectID", null, projectID);
		Thread.sleep(2000);
		selectfromDropDownListbyName("selectedBean.beneficiary", beneficiary, 
				null);
		Thread.sleep(2000);
		ClickSaveButton();
		CheckMessageContains("has been added with success", "message_SUCCESS");
	}

}
