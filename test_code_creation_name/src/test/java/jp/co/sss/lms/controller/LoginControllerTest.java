package jp.co.sss.lms.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginControllerTest {
	/** GoogleChromeDriver. */
	private WebDriver chromeDriver;

	/**
	 * 事前準備.<br>
	 * テストクラス実行時に1度だけ実行します。
	 */
	@BeforeClass
	public static void setUpClass() {

		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");

	}

	/**
	 * 事前準備.<br>
	 * テストケース実行時に実行します。
	 */
	@BeforeAll
	public void setUp() {

		chromeDriver = new ChromeDriver();

		// 各画面表示時に5秒待機する
		chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/**
	 * 後処理.<br>
	 * テストケース実行後に実行します。
	 */
	@AfterAll
	public void down() {

		// chromeドライバーを終了する
		chromeDriver.quit();

	}

	/**
	 * テストケース02
	 * ログイン画面でユーザー、パスワードを入力
	 */
	@Test
	void testIndex() throws IOException {
		// 指定のURLの画面を開く
		chromeDriver.get(
				"http://localhost:8080/lms/");

		//ケース02 START
		// ユーザー,パスワードを入力し、Enterキーを押下する
		chromeDriver.findElement(By.name("loginId")).sendKeys("user");
		chromeDriver.findElement(By.name("password")).sendKeys("StudentAA02", Keys.ENTER);
		//ケース02 FINISH

		File file = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,
				new File(
						"C:\\rp2-pleiades\\ws_rp2\\test_code_creation_innamimarino\\evidence\\\\index_success.png"));
	}
}
