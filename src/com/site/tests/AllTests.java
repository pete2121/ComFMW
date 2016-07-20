package com.site.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CreateTask.class, 
	CreateEvent.class,
	FinishAF.class,
	ApproveAF.class,
	AFeventsSign.class
	})

public class AllTests {

}
