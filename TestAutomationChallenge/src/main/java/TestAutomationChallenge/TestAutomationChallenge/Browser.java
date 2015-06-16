package TestAutomationChallenge.TestAutomationChallenge;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.TestListenerAdapter;

/**
 * This class will create different browser instances.
 * @author ashish.sharda
 *
 */
public class Browser extends TestListenerAdapter  {
	public static WebDriver driver;
	public static String browsername;

	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

	public String getCurrentUrl()
	{
		return driver.getCurrentUrl();
	}

	public String getBrowserName()
	{
		return browsername;
	}


	/**
	 * Get the instance of driver
	 * @return :driver instance.
	 */
	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	/**
	 * Depending on your setup it will either read the properties file from opt/automation-overrides.properties or fallback to your stageconfig.properties file.
	 * @return
	 * @throws IOException
	 */
	public static Properties getBasePropertiesFile(){
		Properties prop = new Properties();
		InputStream input = null;
		String fileName = StringUtils.EMPTY;
		File file;
			fileName = "config.properties";
		
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	
	public void createBrowserInstance(String bname) throws IOException
	{
		Properties prop = getBasePropertiesFile();
		if(bname.equals("FF"))
		{
			driver = new FirefoxDriver();
		}
		else if(bname.equals("Chrome"))
		{

			//Path to Chrome driver.
			browsername=bname;
			String operatingsystem = System.getProperty("os.name").toLowerCase();
			String currentDir = System.getProperty("user.dir");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("--disable-extensions");
			if (operatingsystem.contains("mac"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/"+"chromedriver");
			}
			else if (operatingsystem.contains("win"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/"+"chromedriver 2.exe");
			} 
			driver  = new ChromeDriver(options);
		}

		else if(bname.equals("Safari"))
		{	
			driver  = new SafariDriver();

		}

		threadDriver.set(driver);
	}

}
