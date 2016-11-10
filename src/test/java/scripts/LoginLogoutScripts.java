package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
public class LoginLogoutScripts extends TestBaseSetup {
	private WebDriver driver;
	private LoginLogoutPage loginLogoutPageObj;
	private String expectLoginLabel = "MY DASHBOARD";
	private String expectLogoutLabel = "You have logged out and will be redirected to our homepage in 5 seconds.";
	private String expectLoginReqLabel = "This is a required field.";

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	/**
	 * Login Success
	 * 
	 * @author HangNT
	 * @since 2016/07/13
	 */
	@Test
	public void loginSuccess() {
		loginLogoutPageObj = new LoginLogoutPage(driver);
		loginLogoutPageObj.goToLoginPage();
		loginLogoutPageObj.loginGuru("hangnguyen1611@gmail.com", "123456");
		Assert.assertEquals(loginLogoutPageObj.getLoginSuccessMsg(), expectLoginLabel);
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
		//ReadExcelFile readExcelFileObj = new ReadExcelFile();
//		int rowCount = readExcelFileObj.getRowCount("C:", "Excel.xlsx", "Sheet1");
//		for (int i = 1; i <= rowCount; i++) {
//			String UserName = readExcelFileObj.getCellValue("C:", "Excel.xlsx", "Sheet1", i, 0);
//			String Pwd = readExcelFileObj.getCellValue("C:", "Excel.xlsx", "Sheet1", i, 1);
//			loginLogoutPageObj.loginGuru(UserName, Pwd);
			Assert.assertEquals(loginLogoutPageObj.getEmailReqMsg(), expectLoginReqLabel);
			Assert.assertEquals(loginLogoutPageObj.getPwdReqMsg(), expectLoginReqLabel);
		//}
	}

	/**
	 * Logout
	 * 
	 * @author HangNT
	 * @since 2016/07/13
	 */
	@Test
	public void logout() {
		loginLogoutPageObj = new LoginLogoutPage(driver);
		loginLogoutPageObj.goToLoginPage();
		loginLogoutPageObj.loginGuru("hangnguyen1611@gmail.com", "123456");
		loginLogoutPageObj.logout();
		Assert.assertEquals(loginLogoutPageObj.getLogoutMsg(), expectLogoutLabel);
	}
}
