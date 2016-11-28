package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import scripts.base.TestBaseSetup;
import scripts.page.LoginLogoutPage;

/**
 * 
 * @class LoginLogoutScripts
 * 
 * @author HangNT
 * @since 2017/07/13
 *
 */
public class LoginLogoutScripts {
	private WebDriver driver;
	private LoginLogoutPage loginLogoutPageObj;
	private String expectLoginLabel = "MY DASHBOARD";
	private String expectLogoutLabel = "You have logged out and will be redirected to our homepage in 5 seconds.";
	private String expectLoginReqLabel = "This is a required field.";

	@Parameters({ "baseURL", "browserType" })
	@BeforeClass
	public void setUp(@Optional("http://live.guru99.com") String baseURL, @Optional("phantomjs") String browserType) {
		TestBaseSetup baseObj = new TestBaseSetup(baseURL, browserType);
		driver = baseObj.setDriver();
	}

	/*
	 * @Factory(dataProvider = "setEnv") public void setUp(String baseURL,
	 * String browserType) { TestBaseSetup baseObj = new TestBaseSetup(baseURL,
	 * browserType); driver = baseObj.setDriver(); }
	 * 
	 * @DataProvider public static Object[][] setEnv() { return new Object[][] {
	 * { "http://google.com.vn", "chrome" } }; }
	 */

	/**
	 * Login Success
	 * 
	 * @author HangNT
	 * @since 2016/07/13
	 */
	@Test
	public void loginSuccess() {
		System.out.println("xin chao in");
		loginLogoutPageObj = new LoginLogoutPage(driver);
		loginLogoutPageObj.goToLoginPage();
		loginLogoutPageObj.loginGuru("hangnguyen1611@gmail.com", "123456");
		Assert.assertEquals(loginLogoutPageObj.getLoginSuccessMsg(), expectLoginLabel);
		System.out.println("xin chao in2");

	}

	/**
	 * Login Success
	 * 
	 * @author HangNT
	 * @since 2016/07/13
	 */
	public void loginFail() {
		loginLogoutPageObj = new LoginLogoutPage(driver);
		loginLogoutPageObj.goToLoginPage();
		// ReadExcelFile readExcelFileObj = new ReadExcelFile();
		// int rowCount = readExcelFileObj.getRowCount("C:", "Excel.xlsx",
		// "Sheet1");
		// for (int i = 1; i <= rowCount; i++) {
		// String UserName = readExcelFileObj.getCellValue("C:", "Excel.xlsx",
		// "Sheet1", i, 0);
		// String Pwd = readExcelFileObj.getCellValue("C:", "Excel.xlsx",
		// "Sheet1", i, 1);
		// loginLogoutPageObj.loginGuru(UserName, Pwd);
		Assert.assertEquals(loginLogoutPageObj.getEmailReqMsg(), expectLoginReqLabel);
		Assert.assertEquals(loginLogoutPageObj.getPwdReqMsg(), expectLoginReqLabel);
		// }
	}

	/**
	 * Logout
	 * 
	 * @author HangNT
	 * @since 2016/07/13
	 */

	public void logout() {
		loginLogoutPageObj = new LoginLogoutPage(driver);
		loginLogoutPageObj.goToLoginPage();
		loginLogoutPageObj.loginGuru("hangnguyen1611@gmail.com", "123456");
		loginLogoutPageObj.logout();
		Assert.assertEquals(loginLogoutPageObj.getLogoutMsg(), expectLogoutLabel);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
