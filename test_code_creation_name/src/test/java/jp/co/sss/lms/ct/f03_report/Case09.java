package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 結合テスト レポート機能
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {

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
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
		final WebElement userDetail = webDriver.findElement(By.cssSelector("a[href=\"/lms/user/detail\"]"));

		userDetail.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("ユーザー詳細", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		List<WebElement> detail = webDriver.findElements(By.className("btn-default"));
		WebElement detailButton = detail.get(7);

		scrollTo("document.body.scrollHeight");

		detailButton.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		List<WebElement> learning = webDriver.findElements(By.className("form-control"));
		WebElement learningObjectives = learning.get(0);
		final WebElement submission = webDriver.findElement(By.className("btn-primary"));

		learningObjectives.clear();

		learningObjectives.sendKeys("");

		scrollTo("document.body.scrollHeight");

		submission.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		List<WebElement> learning = webDriver.findElements(By.className("form-control"));
		WebElement learningObjectives = learning.get(0);
		final Select level = new Select(webDriver.findElement(By.name("intFieldValueArray[0]")));
		final WebElement submission = webDriver.findElement(By.className("btn-primary"));

		learningObjectives.clear();

		learningObjectives.sendKeys("case09Test");
		level.selectByIndex(0);

		scrollTo("document.body.scrollHeight");

		submission.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		final WebElement achievementLevel = webDriver.findElement(By.id("content_0"));
		final WebElement submission = webDriver.findElement(By.className("btn-primary"));
		final Select level = new Select(webDriver.findElement(By.name("intFieldValueArray[0]")));

		achievementLevel.clear();

		achievementLevel.sendKeys("a");
		level.selectByIndex(1);

		scrollTo("document.body.scrollHeight");

		submission.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		final WebElement achievementLevel = webDriver.findElement(By.id("content_0"));
		final WebElement submission = webDriver.findElement(By.className("btn-primary"));

		achievementLevel.clear();

		achievementLevel.sendKeys("11");

		scrollTo("document.body.scrollHeight");

		submission.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		final WebElement achievementLevel = webDriver.findElement(By.id("content_0"));
		final WebElement submission = webDriver.findElement(By.className("btn-primary"));
		final WebElement impressions = webDriver.findElement(By.id("content_1"));

		achievementLevel.clear();
		impressions.clear();

		achievementLevel.sendKeys("");
		impressions.sendKeys("");

		scrollTo("document.body.scrollHeight");

		submission.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		scrollTo("150");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		final WebElement achievementLevel = webDriver.findElement(By.id("content_0"));
		final WebElement submission = webDriver.findElement(By.className("btn-primary"));
		final WebElement impressions = webDriver.findElement(By.id("content_1"));
		final WebElement lookingBack = webDriver.findElement(By.id("content_2"));

		achievementLevel.clear();
		impressions.clear();
		lookingBack.clear();

		StringBuilder longTextBuilder = new StringBuilder();
		for (int i = 0; i < 2001; i++) {
			longTextBuilder.append("a");
		}
		String longText = longTextBuilder.toString();

		impressions.sendKeys(longText);
		lookingBack.sendKeys(longText);

		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		achievementLevel.sendKeys("1");

		scrollTo("document.body.scrollHeight");

		submission.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

}
