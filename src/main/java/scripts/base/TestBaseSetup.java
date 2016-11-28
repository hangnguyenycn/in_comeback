package scripts.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @class TestBaseSetup
 * 
 * @author HangNT
 * @since 2016/07/04
 *
 */
public class TestBaseSetup {
	private String baseURL;
	private String browserType;
	private static String homeDir = System.getProperty("user.dir");
	private static String driverPathChrome = homeDir + "\\src\\main\\resources\\";
	private static String driverPathIE = "F:\\HANG\\IEDriverServer_Win32_2.53.1\\";
	private static String driverPathFirefox = homeDir + "\\src\\main\\resources\\";
    private static String driverPathPhantomjs=homeDir+"src\\main\\resources\\phantomjs-2.1.1-windows\\bin\\";
	
	public TestBaseSetup(String baseURL, String browserType) {
		this.baseURL = baseURL;
		this.browserType = browserType;
	}

	/**
	 * set driver
	 * 
	 * @author HangNT
	 * @since 2016/07/04
	 * @param browserType
	 * @param baseURL
	 */
	public WebDriver setDriver() {
		switch (browserType) {
		case "chrome":
			System.out.println("init chrome dirver");
			return initChromeDriver(baseURL);
		case "firefox":
			return initFirefoxDriver(baseURL);
		case "ie":
			return initIEDriver(baseURL);
		case "phantomjs":
			return initPhantomjs(baseURL);
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			return initFirefoxDriver(baseURL);
		}
	}

	private static WebDriver initPhantomjs(String baseURL) {
		System.out.println("Launching Phantomjs with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPathPhantomjs + "phantomjs.exe");
		WebDriver driver = new PhantomJSDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
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
		System.setProperty("webdriver.gecko.driver", driverPathFirefox + "geckodriver.exe");
		// WebDriver driver = new FirefoxDriver();
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);
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
		System.setProperty("webdriver.ie.driver", driverPathIE + "IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

}
