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
import org.openqa.selenium.support.ui.Select;

/**
 * 結合テスト 勤怠管理機能
 * ケース12
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース12 受講生 勤怠直接編集 入力チェック")
public class Case12 {

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
	@DisplayName("テスト04 「勤怠情報を直接編集する」リンクから勤怠情報直接変更画面に遷移")
	void test04() {
		final WebElement userDetail = webDriver.findElement(By.cssSelector("a[href=\"/lms/attendance/update\"]"));

		userDetail.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 不適切な内容で修正してエラー表示：出退勤の（時）と（分）のいずれかが空白")
	void test05() {
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));

		startMinute.selectByIndex(1);
		endHour.selectByIndex(1);
		endMinute.selectByIndex(1);

		scrollTo("document.body.scrollHeight");

		final WebElement userDetail = webDriver.findElement(By.name("complete"));

		userDetail.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		scrollTo("-300");

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正してエラー表示：出勤が空白で退勤に入力あり")
	void test06() {
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));

		startHour.selectByIndex(0);
		startMinute.selectByIndex(0);
		endHour.selectByIndex(1);
		endMinute.selectByIndex(1);

		scrollTo("document.body.scrollHeight");

		final WebElement userDetail = webDriver.findElement(By.name("complete"));

		userDetail.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		scrollTo("-300");

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正してエラー表示：出勤が退勤よりも遅い時間")
	void test07() {
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));

		startHour.selectByIndex(3);
		startMinute.selectByIndex(3);
		endHour.selectByIndex(1);
		endMinute.selectByIndex(1);

		scrollTo("document.body.scrollHeight");

		final WebElement userDetail = webDriver.findElement(By.name("complete"));

		userDetail.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		scrollTo("-300");

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正してエラー表示：出退勤時間を超える中抜け時間")
	void test08() {
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));
		final Select blankTime = new Select(webDriver.findElement(By.name("attendanceList[0].blankTime")));

		startHour.selectByIndex(2);
		startMinute.selectByIndex(1);
		endHour.selectByIndex(3);
		endMinute.selectByIndex(1);
		blankTime.selectByIndex(8);

		scrollTo("document.body.scrollHeight");

		final WebElement userDetail = webDriver.findElement(By.name("complete"));

		userDetail.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		scrollTo("-300");

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正してエラー表示：備考が100文字超")
	void test09() {
		final WebElement note = webDriver.findElement(By.name("attendanceList[0].note"));

		note.clear();

		StringBuilder longTextBuilder = new StringBuilder();
		for (int i = 0; i < 101; i++) {
			longTextBuilder.append("a");
		}
		String longText = longTextBuilder.toString();

		note.sendKeys(longText);

		scrollTo("document.body.scrollHeight");

		final WebElement userDetail = webDriver.findElement(By.name("complete"));

		userDetail.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		scrollTo("-300");

		String pageTitle = webDriver.getTitle();
		assertEquals("勤怠情報変更｜LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

}
