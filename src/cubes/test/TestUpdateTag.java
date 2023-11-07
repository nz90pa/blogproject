package cubes.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.webpages.LoginPage;
import cubes.webpages.tags.TagFormPage;
import cubes.webpages.tags.TagListPage;

public class TestUpdateTag {
	private static ChromeDriver driver;
	private static LoginPage loginPage;
	private static TagFormPage tagFormPage;
	private static TagListPage tagListPage;
	private static String tagName = "";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofMillis(10000));

		loginPage = new LoginPage(driver);
		tagFormPage = new TagFormPage(driver,driverWait);
		tagListPage = new TagListPage(driver, driverWait);
		
		loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		driver.close();
	}

	@BeforeMethod
	public void setUp() throws Exception {
	}

	@AfterMethod
	public void tearDown() throws Exception {
		
	}

	@Test
	public void tc1TestUpdateEmptyTagName() {
		tagListPage.clickOnAddNewTag();
		
		tagName = tagFormPage.addNewRandomTag();
		
		tagListPage.clickOnUpdateTag(tagName);
		
		tagFormPage.inputTagString("");
		
		tagFormPage.clickSave();
		
		String error = tagFormPage.getErrorMessage();
		
		AssertJUnit.assertEquals("This field is required.", error);	
	}
	
	@Test
	public void tc2TestUpdateTagWithExistingName() {
		tagListPage.clickOnUpdateTag(tagName);
		
		tagFormPage.inputTagString("sit");
		
		tagFormPage.clickSave();
		
		String error = tagFormPage.getErrorMessage("The name has already been taken.");
		
		AssertJUnit.assertEquals("The name has already been taken.", error);
		
	}
	
	@Test
	public void tc3TestUpdateTagName() {
		tagListPage.clickOnUpdateTag(tagName);
		
		String newTagName = "New "+tagFormPage.getTagString();
		
		tagFormPage.inputTagString(newTagName);
		
		tagFormPage.clickSave();
		
		AssertJUnit.assertEquals(true, tagListPage.isTagInList(newTagName));
		
		tagName = newTagName;
	}


}
