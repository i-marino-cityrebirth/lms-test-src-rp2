package jp.co.sss.lms.ct.f06_login2;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能②
 * ケース16
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース16 受講生 初回ログイン 変更パスワード未入力")
public class Case16 {

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
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		// ユーザー,パスワードを入力し、Enterキーを押下する
		final WebElement loginId = webDriver.findElement(By.name("loginId"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement login = webDriver.findElement(By.className("btn-primary"));

		loginId.clear();
		password.clear();

		loginId.sendKeys("StudentAA08");
		password.sendKeys("StudentAA08");
		login.sendKeys(Keys.ENTER);

		String pageTitle = webDriver.getTitle();
		assertEquals("セキュリティ規約 | LMS", pageTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() throws InterruptedException {
		final WebElement consent = webDriver.findElement(By.className("btn-primary"));
		final WebElement securityCheck = webDriver.findElement(By.name("securityFlg"));

		scrollTo("document.body.scrollHeight");

		securityCheck.click();

		consent.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() throws InterruptedException {

		List<WebElement> change = webDriver.findElements(By.className("btn-primary"));
		WebElement changeButton = change.get(1);

		scrollTo("document.body.scrollHeight");

		changeButton.click();

		//モーダル内のボタンを探してクリック
		Thread.sleep(2000);

		final WebElement modalChange = webDriver.findElement(By.id("upd-btn"));

		modalChange.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() throws InterruptedException {

		final WebElement password = webDriver.findElement(By.id("currentPassword"));
		final WebElement newPassword = webDriver.findElement(By.id("password"));
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		List<WebElement> change = webDriver.findElements(By.className("btn-primary"));
		WebElement changeButton = change.get(1);

		password.sendKeys("StudentAA08");
		newPassword.sendKeys("0---+----1----+----2-");
		passwordConfirm.sendKeys("0---+----1----+----2-");

		scrollTo("document.body.scrollHeight");

		changeButton.click();

		Thread.sleep(2000);

		final WebElement modalChange = webDriver.findElement(By.id("upd-btn"));

		modalChange.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() throws InterruptedException {

		final WebElement password = webDriver.findElement(By.id("currentPassword"));
		final WebElement newPassword = webDriver.findElement(By.id("password"));
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		List<WebElement> change = webDriver.findElements(By.className("btn-primary"));
		WebElement changeButton = change.get(1);

		password.sendKeys("StudentAA08");
		newPassword.sendKeys("1");
		passwordConfirm.sendKeys("1");

		scrollTo("document.body.scrollHeight");

		changeButton.click();

		//モーダル内のボタンを探してクリック
		Thread.sleep(2000);

		final WebElement modalChange = webDriver.findElement(By.id("upd-btn"));

		modalChange.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() throws InterruptedException {

		final WebElement password = webDriver.findElement(By.id("currentPassword"));
		final WebElement newPassword = webDriver.findElement(By.id("password"));
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		List<WebElement> change = webDriver.findElements(By.className("btn-primary"));
		WebElement changeButton = change.get(1);

		password.sendKeys("StudentAA08");
		newPassword.sendKeys("StudentAA09");
		passwordConfirm.sendKeys("StudentAA08");

		scrollTo("document.body.scrollHeight");

		changeButton.click();

		//モーダル内のボタンを探してクリック
		Thread.sleep(2000);

		final WebElement modalChange = webDriver.findElement(By.id("upd-btn"));

		modalChange.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);

		scrollTo("document.body.scrollHeight");

		getEvidence(new Object() {
		});
	}

}
