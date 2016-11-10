package scripts.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @class TestBaseSetup
 * 
 * @author HangNT
 * @since 2016/07/04
 *
 */
public class TestBaseSetup {
	private WebDriver driver;
	private static String homeDir = System.getProperty("user.dir");	
	private static String driverPathChrome = homeDir+"\\src\\main\\resources\\";
	static String driverPathIE = "F:\\HANG\\IEDriverServer_Win32_2.53.1\\";

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * set driver
	 * 
	 * @author HangNT
	 * @since 2016/07/04
	 * @param browserType
	 * @param baseURL
	 */
	private void setDriver(String browserType, String baseURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(baseURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(baseURL);
			break;
		case "ie":
			driver = initIEDriver(baseURL);
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(baseURL);
		}
	}

	/**
	 * init chrome driver
	 * 
	 * @author HangNT
	 * @since 2016/07/04
	 * @param baseURL
	 * @return
	 */
	private static WebDriver initChromeDriver(String baseURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPathChrome + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

	/**
	 * init firefox driver
	 * 
	 * @author HangNT
	 * @since 2016/07/04
	 * @param baseURL
	 * @return
	 */
	private static WebDriver initFirefoxDriver(String baseURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

	/**
	 * init IE driver
	 * 
	 * @author HangNT
	 * @since 2016/07/04
	 * @param baseURL
	 * @return
	 */
	private static WebDriver initIEDriver(String baseURL) {
		System.out.println("Launching Internet Explorer browser..");
		System.setProperty("webdriver.chrome.driver", "F:\\HANG\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

	/**
	 * initializeTestBaseSetup
	 * 
	 * @author HangNT
	 * @since 2016/07/04
	 * @param browserType
	 * @param baseURL
	 */
	@Parameters({ "browserType", "baseURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String baseURL) {
		try {
			setDriver(browserType, baseURL);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
