package jp.co.sss.lms.ct.f05_exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

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
 * 結合テスト 試験実施機能
 * ケース14
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース13 受講生 試験の実施 結果50点")
public class Case14 {

	/** テスト07およびテスト08 試験実施日時 */
	static Date date;

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
	@DisplayName("テスト03 「試験有」の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		List<WebElement> detail = webDriver.findElements(By.className("btn-default"));
		WebElement detailNumber = detail.get(3);

		detailNumber.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("セクション詳細 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「本日の試験」エリアの「詳細」ボタンを押下し試験開始画面に遷移")
	void test04() {
		List<WebElement> detail = webDriver.findElements(By.className("btn-default"));
		WebElement detailNumber = detail.get(1);

		detailNumber.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("試験【ITリテラシー①】 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「試験を開始する」ボタンを押下し試験問題画面に遷移")
	void test05() {
		final WebElement testStart = webDriver.findElement(By.className("btn-primary"));

		testStart.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("ITリテラシー① | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 正答と誤答が半々で「確認画面へ進む」ボタンを押下し試験回答確認画面に遷移")
	void test06() throws InterruptedException {

		//正答
		final WebElement answer0 = webDriver.findElement(By.id("answer-0-2"));
		final WebElement answer1 = webDriver.findElement(By.id("answer-1-2"));
		final WebElement answer2 = webDriver.findElement(By.id("answer-2-0"));
		final WebElement answer3 = webDriver.findElement(By.id("answer-3-0"));
		final WebElement answer4 = webDriver.findElement(By.id("answer-4-1"));
		final WebElement answer5 = webDriver.findElement(By.id("answer-5-1"));

		//誤答
		final WebElement answer6 = webDriver.findElement(By.id("answer-6-0"));
		final WebElement answer7 = webDriver.findElement(By.id("answer-7-0"));
		final WebElement answer8 = webDriver.findElement(By.id("answer-8-0"));
		final WebElement answer9 = webDriver.findElement(By.id("answer-9-0"));
		final WebElement answer10 = webDriver.findElement(By.id("answer-10-0"));
		final WebElement answer11 = webDriver.findElement(By.id("answer-11-0"));

		answer0.click();
		scrollBy("500");

		answer1.click();
		scrollBy("300");

		answer2.click();
		scrollBy("400");

		answer3.click();
		scrollBy("300");

		answer4.click();
		scrollBy("400");

		answer5.click();
		scrollBy("400");

		answer6.click();
		scrollBy("400");

		answer7.click();
		scrollBy("300");

		answer8.click();
		scrollBy("400");

		answer9.click();
		scrollBy("400");

		answer10.click();
		scrollBy("200");

		answer11.click();

		scrollTo("document.body.scrollHeight");

		Thread.sleep(2000);

		List<WebElement> testCheck = webDriver.findElements(By.className("btn-primary"));
		WebElement check = testCheck.get(1);

		check.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("ITリテラシー① | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 「回答を送信する」ボタンを押下し試験結果画面に遷移")
	void test07() throws InterruptedException {

		scrollTo("document.body.scrollHeight");

		Thread.sleep(2000);

		final WebElement send = webDriver.findElement(By.id("sendButton"));

		send.click();

		Alert alert = webDriver.switchTo().alert();

		alert.accept();

		String pageTitle = webDriver.getTitle();
		assertEquals("ITリテラシー① | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 「戻る」ボタンを押下し試験開始画面に遷移後当該試験の結果が反映される")
	void test08() {

		scrollTo("document.body.scrollHeight");

		final WebElement back = webDriver.findElement(By.className("btn-primary"));

		back.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("試験【ITリテラシー①】 | LMS", pageTitle);

		getEvidence(new Object() {
		});

	}

}
