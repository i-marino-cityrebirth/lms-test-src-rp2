package jp.co.sss.lms.ct.f04_attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト 勤怠管理機能
 * ケース10
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース10 受講生 勤怠登録 正常系")
public class Case10 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo("http://localhost:8080/lms/");
		String pageTitle = webDriver.getTitle();
		assertEquals("ログイン | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ユーザー,パスワードを入力し、Enterキーを押下する
		final WebElement loginId = webDriver.findElement(By.name("loginId"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement login = webDriver.findElement(By.className("btn-primary"));

		loginId.clear();
		password.clear();

		loginId.sendKeys("StudentAA01");
		password.sendKeys("StudentAA02");
		login.sendKeys(Keys.ENTER);

		String pageTitle = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() {
		final WebElement userDetail = webDriver.findElement(By.cssSelector("a[href=\"/lms/attendance/detail\"]"));

		userDetail.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「出勤」ボタンを押下し出勤時間を登録")
	void test04() {
		final WebElement punchIn = webDriver.findElement(By.name("punchIn"));

		punchIn.click();

		Alert alert = webDriver.switchTo().alert();
		alert.accept();

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「退勤」ボタンを押下し退勤時間を登録")
	void test05() {
		final WebElement punchOut = webDriver.findElement(By.name("punchOut"));

		punchOut.click();

		Alert alert = webDriver.switchTo().alert();
		alert.accept();

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

}
