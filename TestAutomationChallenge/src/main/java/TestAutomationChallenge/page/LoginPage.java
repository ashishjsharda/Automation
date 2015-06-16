package TestAutomationChallenge.page;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import TestAutomationChallenge.TestAutomationChallenge.BaseUISetUp;
import TestAutomationChallenge.TestAutomationChallenge.Browser;

/**
 * Login Page POM.
 * @author Ashish
 *
 */
public class LoginPage extends Browser{
	private WebDriver driver;
	InputStream input = null;
	Properties prop = new Properties();

	public LoginPage(WebDriver driver) throws Exception {
		this.driver = driver;

	}

	/**
	 * Method to login in Webinar.
	 * @return MyWebinar Page.
	 */
	public MyWebinar login() {

		try
		{
			input = new FileInputStream("login.properties");
			prop.load(input);
			userName.clear();
			userName.sendKeys(prop.getProperty("username"));
			password.clear();
			password.sendKeys(prop.getProperty("password"));
			submit.click();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return PageFactory.initElements(BaseUISetUp.getDriver(), MyWebinar.class);
	}

	
	@FindBy(css="#emailAddress")
	WebElement userName;

	@FindBy(css="#password")
	WebElement password;

	@FindBy(css="#submit")
	WebElement submit;


}



