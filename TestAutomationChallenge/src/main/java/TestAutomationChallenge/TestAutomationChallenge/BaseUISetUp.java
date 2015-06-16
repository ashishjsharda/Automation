package TestAutomationChallenge.TestAutomationChallenge;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUISetUp extends Browser
{
	
	protected String appURL;
	protected Properties prop = new Properties();

	/**
	 * Setup Browser Instance.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException 
	{
		Properties prop = getBasePropertiesFile();
		createBrowserInstance(prop.getProperty("browsers"));
		getDriver().get(prop.getProperty("baseUrl"));
		getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/**
	 * Quit the browser session.
	 * @param result
	 * @throws IOException
	 */
	@AfterMethod
	public void closeBrowser(ITestResult result) throws IOException {

		if (getDriver() != null && !result.isSuccess()) {
			String methodname = result.getName();
			WebDriver augmentedDriver = new Augmenter().augment(getDriver());
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("MMM_dd_HH_mm");
			Date date = new Date();
			String[] splited = methodname.split("\\s+");
			FileUtils.copyFile(scrFile, new File(("./test-output/archive/"+ "screenshots/" + splited[0] + dateFormat.format(date) + ".png")));
			getDriver().quit();

		}

		else 
		{
			getDriver().quit();
		}
	}



}