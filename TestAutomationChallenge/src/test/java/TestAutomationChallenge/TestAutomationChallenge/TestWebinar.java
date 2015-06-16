package TestAutomationChallenge.TestAutomationChallenge;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestAutomationChallenge.page.LoginPage;
import TestAutomationChallenge.page.MyWebinar;

/**
 * Test Class to test MyWebinars.
 * @author Ashish
 *
 */
public class TestWebinar extends BaseUISetUp {

	LoginPage loginPage;
	MyWebinar myWebinar;

	@BeforeMethod
	public void login()
	{
		loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
		myWebinar=loginPage.login();
	}

	/**
	 * Login to Webinar.
	 * (2)Schedule a Webinar.
	 * (3)Test Date and Time of Webinar Scheduled.
	 * @throws InterruptedException
	 */
	@Test
	public void testMyWebinar() throws InterruptedException
	{
		myWebinar.clickScheduleWebinar();
		myWebinar.enterTitle();
		myWebinar.selectStartDate();
		String date=myWebinar.getDayAndTime();
		myWebinar.clickSchedule();
		myWebinar.getDateAndTime();
		Assert.assertEquals(date, myWebinar.getDateAndTime(), "Inorrect date seen");

	}
}
