package jp.co.sss.lms.ct.f05_exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

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
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

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
		// TODO ここに追加
		goTo("http://localhost:8080/lms");
		String pageTitle = WebDriverUtils.webDriver.getTitle();
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
		String pageTitle = WebDriverUtils.webDriver.getTitle();
		assertEquals("コース詳細 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「試験有」の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		final List<WebElement> dtail = webDriver.findElements(By.cssSelector("input.btn-default[type='submit']"));
		dtail.get(1).click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = WebDriverUtils.webDriver.getTitle();
		assertEquals("セクション詳細 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「本日の試験」エリアの「詳細」ボタンを押下し試験開始画面に遷移")
	void test04() {
		// TODO ここに追加
		final List<WebElement> dtail = webDriver.findElements(By.cssSelector("input.btn-default[type='submit']"));
		dtail.get(0).click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = WebDriverUtils.webDriver.getTitle();
		assertEquals("試験【ITリテラシー①】 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「試験を開始する」ボタンを押下し試験問題画面に遷移")
	void test05() {
		// TODO ここに追加
		final WebElement testStart = webDriver.findElement(By.cssSelector("input.btn-primary[type='submit']"));
		testStart.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = WebDriverUtils.webDriver.getTitle();
		assertEquals("ITリテラシー① | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 正答と誤答が半々で「確認画面へ進む」ボタンを押下し試験回答確認画面に遷移")
	void test06() throws InterruptedException {
		// TODO ここに追加
		final List<WebElement> answer = webDriver.findElements(By.cssSelector("input[type='radio']"));
		//第１問
		answer.get(0).click();
		scrollBy("400");
		//第２問
		answer.get(6).click();
		scrollBy("400");
		//第３問
		answer.get(8).click();
		scrollBy("400");
		//第４問
		answer.get(15).click();
		scrollBy("400");
		//第５問
		answer.get(19).click();
		scrollBy("400");
		//第６問
		answer.get(22).click();
		scrollBy("400");
		//第7問
		answer.get(27).click();
		scrollBy("400");
		//第8問
		answer.get(31).click();
		scrollBy("350");
		//第9問
		answer.get(33).click();
		scrollBy("350");
		//第10問
		answer.get(37).click();
		scrollBy("350");
		//第11門
		answer.get(42).click();
		scrollBy("350");
		//第12問
		answer.get(46).click();
		scrollTo("document.body.scrollHeight");
		final WebElement confirmation = webDriver.findElement(By.cssSelector("input.btn-primary[type='submit']"));
		confirmation.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = WebDriverUtils.webDriver.getTitle();
		assertEquals("ITリテラシー① | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 「回答を送信する」ボタンを押下し試験結果画面に遷移")
	void test07() throws InterruptedException {
		// TODO ここに追加
		scrollTo("document.body.scrollHeight");
		Thread.sleep(5000);
		final WebElement sendAnswer = webDriver.findElement(By.id("sendButton"));
		sendAnswer.click();
		final Alert alert = webDriver.switchTo().alert();
		alert.accept();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = WebDriverUtils.webDriver.getTitle();
		assertEquals("ITリテラシー① | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 「戻る」ボタンを押下し試験開始画面に遷移後当該試験の結果が反映される")
	void test08() {
		// TODO ここに追加
		scrollTo("document.body.scrollHeight");
		final WebElement back = webDriver.findElement(By.cssSelector("input.btn-primary[type='submit']"));
		back.click();
		visibilityTimeout(By.className("container"), 10);
		String pageTitle = webDriver.getTitle();
		assertEquals("試験【ITリテラシー①】 | LMS", pageTitle);
		getEvidence(new Object() {
		});
	}

}
