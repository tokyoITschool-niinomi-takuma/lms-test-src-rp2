package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
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
		// TODO ここに追加
		goTo("http://localhost:8080/lms");
		String pageTitle = webDriver.getTitle();
		assertEquals("ログイン | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		final WebElement loginId = webDriver.findElement(By.name("loginId"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement login = webDriver.findElement(By.className("btn-primary"));
		loginId.clear();
		loginId.sendKeys("StudentAA01");
		password.clear();
		password.sendKeys("TestCaseAAA001");
		login.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		final WebElement welcome = webDriver.findElement(By.linkText("ようこそ受講生ＡＡ１さん"));
		welcome.click();
		String pageTitle = webDriver.getTitle();
		assertEquals("ユーザー詳細", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		scrollBy("1000");
		final List<WebElement> detail = webDriver.findElements(By.className("btn-default"));
		detail.get(7).click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		// TODO ここに追加
		final WebElement topics = webDriver.findElement(By.name("intFieldNameArray[0]"));
		topics.clear();
		final Select level = new Select(webDriver.findElement(By.name("intFieldValueArray[0]")));
		level.selectByIndex(2);
		scrollTo("document.body.scrollHeight");
		final WebElement submission = webDriver.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		submission.click();
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		// TODO ここに追加
		final WebElement topics = webDriver.findElement(By.name("intFieldNameArray[0]"));
		topics.clear();
		topics.sendKeys("java");
		final Select level = new Select(webDriver.findElement(By.name("intFieldValueArray[0]")));
		level.selectByIndex(0);
		scrollTo("document.body.scrollHeight");
		final WebElement submission = webDriver.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		submission.click();
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		// TODO ここに追加
		final Select level = new Select(webDriver.findElement(By.name("intFieldValueArray[0]")));
		level.selectByIndex(2);
		final WebElement achievementLevel = webDriver.findElement(By.name("contentArray[0]"));
		achievementLevel.clear();
		achievementLevel.sendKeys("A");
		scrollTo("document.body.scrollHeight");
		final WebElement submission = webDriver.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		submission.click();
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		// TODO ここに追加
		final WebElement achievementLevel = webDriver.findElement(By.name("contentArray[0]"));
		achievementLevel.clear();
		achievementLevel.sendKeys("0");
		scrollTo("document.body.scrollHeight");
		final WebElement submission = webDriver.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		submission.click();
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		// TODO ここに追加
		final WebElement achievementLevel = webDriver.findElement(By.name("contentArray[0]"));
		achievementLevel.clear();
		final WebElement impressions = webDriver.findElement(By.name("contentArray[1]"));
		impressions.clear();
		scrollTo("document.body.scrollHeight");
		final WebElement submission = webDriver.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		submission.click();
		visibilityTimeout(By.className("container"), 10);
		scrollBy("100");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		// TODO ここに追加
		String str = "ABCDEFGHIJ".repeat(200);
		final WebElement achievementLevel = webDriver.findElement(By.name("contentArray[0]"));
		achievementLevel.clear();
		achievementLevel.sendKeys("5");
		final WebElement impressions = webDriver.findElement(By.name("contentArray[1]"));
		impressions.clear();
		impressions.sendKeys(str);
		impressions.sendKeys("Z");
		final WebElement lookingBack = webDriver.findElement(By.name("contentArray[2]"));
		lookingBack.clear();
		lookingBack.sendKeys(str);
		lookingBack.sendKeys("Z");
		scrollTo("document.body.scrollHeight");
		final WebElement submission = webDriver.findElement(By.cssSelector("button.btn-primary[type='submit']"));
		submission.click();
		visibilityTimeout(By.className("container"), 10);
		scrollBy("300");
		getEvidence(new Object() {
		});
	}

}
