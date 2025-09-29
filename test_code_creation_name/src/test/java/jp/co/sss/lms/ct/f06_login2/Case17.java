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
 * ケース17
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース17 受講生 初回ログイン 正常系")
public class Case17 {

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
		loginId.sendKeys("StudentAA03");
		password.clear();
		password.sendKeys("StudentAA03");
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
	@DisplayName("テスト04 変更パスワードを入力し「変更」ボタン押下")
	void test04() throws InterruptedException {
		// TODO ここに追加
		final WebElement nowPassword = webDriver.findElement(By.xpath("//*[@id=\"currentPassword\"]"));
		nowPassword.clear();
		nowPassword.sendKeys("StudentAA03");
		final WebElement newPassword = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
		newPassword.clear();
		newPassword.sendKeys("TestCaseAAA003");
		final WebElement confirmPassword = webDriver.findElement(By.xpath("//*[@id=\"passwordConfirm\"]"));
		confirmPassword.clear();
		confirmPassword.sendKeys("TestCaseAAA003");
		final WebElement change = webDriver
				.findElement(By.xpath("//*[@id=\"upd-form\"]/div[1]/fieldset/div[4]/div/button[2]"));
		change.click();
		Thread.sleep(3000);
		final WebElement confirmChange = webDriver.findElement(By.xpath("//*[@id=\"upd-btn\"]"));
		confirmChange.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}
}
