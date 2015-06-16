package TestAutomationChallenge.TestAutomationChallenge;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility Class that contains list of commonly used Utils.
 * @author Ashish
 *
 */
public class Utility extends BaseUISetUp{

	WebDriver driver;
	static boolean compare;
	public Utility()
	{
		this.driver=driver;
	}


	/**
	 * Wait till element is visible.
	 *
	 * @param driver the driver
	 * @param timeoutSeconds the timeout seconds
	 * @param element the element
	 * @return WebElement
	 */
	public WebElement waitTillVisible( WebDriver driver,  int timeoutSeconds,WebElement element) {
		return new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.visibilityOf(element));
	}
}