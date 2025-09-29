package jp.co.sss.lms.ct.f06_login2;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
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
		// TODO ここに追加
		goTo("http://localhost:8080/lms");
		String pageTitle = webDriver.getTitle();
		assertEquals("ログイン | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		final WebElement loginId = webDriver.findElement(By.name("loginId"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement login = webDriver.findElement(By.className("btn-primary"));
		loginId.clear();
		loginId.sendKeys("StudentAA02");
		password.clear();
		password.sendKeys("StudentAA02");
		login.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("セキュリティ規約 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() {
		// TODO ここに追加
		final WebElement agree = webDriver
				.findElement(By.xpath("//*[@id=\"main\"]/div[2]/form/fieldset/div[1]/div/label/input[1]"));
		agree.click();
		final WebElement next = webDriver.findElement(By.className("btn-primary"));
		next.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() throws InterruptedException {
		// TODO ここに追加
		final WebElement change = webDriver
				.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		change.click();
		Thread.sleep(3000);
		final WebElement confirmChange = webDriver.findElement(By.xpath("//*[@id=\"upd-btn\"]"));
		confirmChange.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() throws InterruptedException {
		// TODO ここに追加
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		currentPassword.sendKeys("StudentAA02");
		String str = "Ab777".repeat(20);
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(str + "Z");
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys(str + "Z");
		scrollTo("document.body.scrollHeight");
		final WebElement change = webDriver
				.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		change.click();
		Thread.sleep(3000);
		final WebElement confirmChange = webDriver.findElement(By.xpath("//*[@id=\"upd-btn\"]"));
		confirmChange.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() throws InterruptedException {
		// TODO ここに追加 
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		currentPassword.sendKeys("StudentAA02");
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("$$$$$$$$");
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys("$$$$$$$$");
		scrollTo("document.body.scrollHeight");
		final WebElement change = webDriver
				.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		change.click();
		Thread.sleep(3000);
		final WebElement confirmChange = webDriver.findElement(By.xpath("//*[@id=\"upd-btn\"]"));
		confirmChange.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() throws InterruptedException {
		// TODO ここに追加
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		currentPassword.sendKeys("StudentAA02");
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("TestCaseAAA003");
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys("TestCaseBBB777");
		scrollTo("document.body.scrollHeight");
		final WebElement change = webDriver
				.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		change.click();
		Thread.sleep(3000);
		final WebElement confirmChange = webDriver.findElement(By.xpath("//*[@id=\"upd-btn\"]"));
		confirmChange.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("パスワード変更 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

}
