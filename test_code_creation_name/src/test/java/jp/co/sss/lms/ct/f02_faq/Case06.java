package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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

		//カレントウインドウのサイズを設定する
		Dimension targetSize = new Dimension(900, 800);
		webDriver.manage().window().setSize(targetSize);

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
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		WebElement dropDown = webDriver.findElement(By.className("dropdown"));
		dropDown.click();

		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));
		helpLink.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("ヘルプ | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		WebElement question = webDriver.findElement(By.linkText("よくある質問"));
		question.click();

		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		webDriver.switchTo().window((String) windowHandles[1]);

		String pageTitle = webDriver.getTitle();
		assertEquals("よくある質問 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {

		List<WebElement> category = webDriver.findElements(By.cssSelector(
				"a[href*='/faq?frequentlyAskedQuestionCategoryId=']"));
		WebElement firstCategory = category.get(0);

		firstCategory.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("よくある質問 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		List<WebElement> searchResults = webDriver.findElements(By.id("question-h[${status.index}]"));
		WebElement firstResults = searchResults.get(0);

		firstResults.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("よくある質問 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

}
