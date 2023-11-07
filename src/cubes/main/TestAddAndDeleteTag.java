
package cubes.main;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddAndDeleteTag {

	private static ChromeDriver driver;
	private static String urlLoginPage = "http://testblog.kurs-qa.cubes.edu.rs/login";
	private static WebDriverWait wait;
	private static String tagName;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver.get(urlLoginPage);
		driver.manage().window().maximize();

		WebElement weEmail = driver.findElement(By.name("email"));
		WebElement wePassword = driver.findElement(By.name("password"));
		WebElement weButton = driver.findElement(By.xpath("//button[@type='submit']"));
		
		weEmail.sendKeys("kursqa@cubes.edu.rs");
		wePassword.sendKeys("cubesqa");
		weButton.click();
				
	}

	@AfterMethod
	public void tearDown() throws Exception {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='toast-message']"))));
		WebElement weProfile = driver.findElement(By.xpath("//i[@class='far fa-user']"));
		weProfile.click();
		
		WebElement weLogout = driver.findElement(By.xpath("//a[@href='https://testblog.kurs-qa.cubes.edu.rs/logout']"));
		weLogout.click();
	}

	@Test
	public void testAddTag() {
		driver.navigate().to("https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
			
		WebElement weTagName = driver.findElement(By.name("name"));
		
		Random random = new Random();
		tagName = "Tag name "+random.nextInt(100);
		
		weTagName.sendKeys(tagName);
	
		WebElement weButtonSaveTag = driver.findElement(By.xpath("//button[@type='submit']"));
		weButtonSaveTag.click();
		
	}
	
	@Test
	public void testDeleteTag() {
		driver.navigate().to("https://testblog.kurs-qa.cubes.edu.rs/admin/tags");


		WebElement weDeleteButton = driver.findElement(By.xpath("//strong[text()='"+tagName+"']//ancestor::tr//td[5]//button"));
		weDeleteButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='btn btn-danger']"))));
		
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
	}

}

