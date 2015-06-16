
package TestAutomationChallenge.page;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import TestAutomationChallenge.TestAutomationChallenge.Browser;
import TestAutomationChallenge.TestAutomationChallenge.Utility;

/**
 * MyWebinar POM.
 * @author Ashish
 *
 */
public class MyWebinar extends Browser{

	private WebDriver driver;
	Utility util=new Utility();
	public  MyWebinar(WebDriver driver) {
		this.driver= driver;

	}

	/**
	 * Click Schedule Webinar.
	 */
	public void clickScheduleWebinar()
	{
		util.waitTillVisible(driver, 5, scheduleWebinar);
		scheduleWebinar.click();
	}

	/**
	 * Enter Title of Webinar.
	 */
	public void enterTitle()
	{
		title.clear();
		Calendar cal = Calendar.getInstance();
		title.sendKeys("Test"+cal.getTimeInMillis());
	}

	/**
	 * Select 3 days later from today's date.
	 */
	public void selectStartDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		int day=Integer.parseInt(dateFormat.format(cal.getTime()));
		day=day+3;
		startDate.click();
		for(WebElement element:days)
		{
			if(element.getText().equals(Integer.toString(day)))
			{
				element.click();
				break;
			}
		}
	}

	/**
	 * Click Schedule button.
	 */
	public void clickSchedule()
	{
		schedule.click();
	}

	/**
	 *Generate Date.
	 * @return date
	 */
	public String getDayAndTime()
	{
		return startDateText.getAttribute("value") +" " +startTime.getAttribute("value") +" " +startTimeOfDay.getText() +" " +"-"+" "+endTime.getAttribute("value")+" "+endTimeOfDay.getText()+" "+"PDT";

	}
	/**
	 * Get Date and Time of Landing Page.
	 * @return
	 */
	public String getDateAndTime()
	{
		util.waitTillVisible(driver, 10, datepicker);
		return datepicker.getText();
	}
	@FindBy(css=".sidebar-nav > li:nth-child(2) > a:nth-child(1)")
	WebElement scheduleWebinar;

	@FindBy(css="#name")
	WebElement title;

	@FindBy(css=".ui-state-default")
	List<WebElement> days;

	@FindBy(css=".ui-datepicker-trigger[alt='Choose a date']")
	WebElement startDate;

	@FindBy(css=".btn-bg")
	WebElement schedule;

	@FindBy(css=".text.startTime")
	WebElement startTime;

	@FindBy(css=".text.endTime")
	WebElement endTime;

	@FindBy(css="#webinarTimesForm_dateTimes_0_startAmPm_trig > span:nth-child(1) > span:nth-child(1)")
	WebElement startTimeOfDay;

	@FindBy(css="#webinarTimesForm_dateTimes_0_endAmPm_trig > span:nth-child(1) > span:nth-child(1)")
	WebElement endTimeOfDay;

	@FindBy(css=".time")
	WebElement datepicker;

	@FindBy(css="#webinarTimesForm_dateTimes_0_startAmPm_trig >span >span")
	WebElement starthour;

	@FindBy(css="#webinarTimesForm_dateTimes_0_endAmPm_trig >span >span")
	WebElement endhour;

	@FindBy(css=".text.start-datepicker.hasDatepicker")
	WebElement startDateText;

}